package com.vinhthanh2.lophocdientu.service;

import com.vinhthanh2.lophocdientu.dto.res.FileResponse;
import com.vinhthanh2.lophocdientu.entity.File;
import com.vinhthanh2.lophocdientu.repository.FileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepo fileRepository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Value("${server.public-url:http://localhost:8080}")
    private String publicUrl;

    public FileResponse upload(MultipartFile file) throws IOException {
        Files.createDirectories(Paths.get(uploadDir));

        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, storedName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        File entity = File.builder()
                .fileName(file.getOriginalFilename())
                .storedName(storedName)
                .url(publicUrl + "/files/" + storedName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .uploadedAt(LocalDateTime.now())
                .build();

        fileRepository.save(entity);

        return mapToResponse(entity);
    }

    public Page<FileResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fileRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public void delete(Long id) throws IOException {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        Path path = Paths.get(uploadDir, file.getStoredName());
        Files.deleteIfExists(path);
        fileRepository.delete(file);
    }

    private FileResponse mapToResponse(File e) {
        return FileResponse.builder()
                .id(e.getId())
                .fileName(e.getFileName())
                .url(e.getUrl())
                .contentType(e.getContentType())
                .size(e.getSize())
                .uploadedAt(e.getUploadedAt())
                .build();
    }
}
