package com.vinhthanh2.lophocdientu.controller;

import com.vinhthanh2.lophocdientu.entity.HocSinh;
import com.vinhthanh2.lophocdientu.service.HocSinhService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/students")
@Getter
@Setter
@AllArgsConstructor
public class HocSinhController {
    private final HocSinhService hocSinhService;

    @GetMapping
    public List<HocSinh> getAll() {
        return hocSinhService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HocSinh> getById(@PathVariable Long id) {
        return hocSinhService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HocSinh create(@RequestBody HocSinh hs) {
        return hocSinhService.save(hs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HocSinh> update(@PathVariable Long id, @RequestBody HocSinh hs) {
        return hocSinhService.getById(id).map(existing -> {
            hs.setId(id);
            return ResponseEntity.ok(hocSinhService.save(hs));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hocSinhService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            hocSinhService.importFromExcel(file);
            return ResponseEntity.ok("Import thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi import: " + e.getMessage());
        }
    }
}
