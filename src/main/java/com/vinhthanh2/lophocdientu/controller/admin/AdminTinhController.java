package com.vinhthanh2.lophocdientu.controller.admin;

import com.vinhthanh2.lophocdientu.dto.req.TinhReq;
import com.vinhthanh2.lophocdientu.service.GiaoVienService;
import com.vinhthanh2.lophocdientu.service.TinhService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/quan-tri/tinh")
@Getter
@Setter
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminTinhController {
    final private TinhService tinhService;
    final private GiaoVienService giaoVienService;

    @GetMapping
    public ResponseEntity<?> layDsTinh(@RequestParam(required = false, defaultValue = "") String search,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tinhService.layDsTinh(search, page, size));
    }

    @PostMapping
    public ResponseEntity<?> taoTinh(@RequestBody TinhReq tinhReq) {
        return ResponseEntity.ok(tinhService.taoTinh(tinhReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaTinh(@PathVariable Long id, @RequestBody TinhReq tinhReq) {
        return ResponseEntity.ok(tinhService.suaTinh(id, tinhReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaTinh(@PathVariable Long id) {
        tinhService.xoaTinh(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/importer/template")
    public ResponseEntity<byte[]> downloadTemplate() throws IOException {
        // Lấy file từ resources
        Resource resource = new ClassPathResource("templates/mau_import_tinh.xlsx");

        // Đọc nội dung file
        byte[] fileBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mau_import_tinh.xlsx")
                .body(fileBytes);
    }

    @PostMapping(value = "/importer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importTinh(@RequestParam("file") MultipartFile file) throws IOException {
        tinhService.importTinh(file);
        return ResponseEntity.ok().build();
    }

}
