package com.vinhthanh2.lophocdientu.controller.admin;

import com.vinhthanh2.lophocdientu.dto.req.UpdatePassReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdateTeacherReq;
import com.vinhthanh2.lophocdientu.service.AuthService;
import com.vinhthanh2.lophocdientu.service.GiaoVienService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quantri/giaovien")
@Getter
@Setter
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminGiaoVienController {
    private final GiaoVienService giaoVienService;
    private final AuthService authService;

    @GetMapping()
    public ResponseEntity<?> layDsGiaoVien(@RequestParam(required = false, defaultValue = "") String search,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(giaoVienService.layDsGiaoVien(search, page, size));
    }

    @GetMapping("/truong/{lopId}")
    public ResponseEntity<?> layDsGiaoVienTheoTruong(@PathVariable Long truong,
                                                     @RequestParam(required = false, defaultValue = "") String search,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(giaoVienService.layGiaoVienTheoTruong(truong, search, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> layGiaoVienTheoId(@PathVariable Long id) {
        return ResponseEntity.ok(giaoVienService.layGiaoVienTheoId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaGiaoVien(Long id, UpdateTeacherReq updateTeacherReq) {
        return ResponseEntity.ok(giaoVienService.suaGiaoVien(id, updateTeacherReq));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> datLaiMatKhau(Long id, UpdatePassReq updatePassReq) {
        authService.doiMatKhau(id, updatePassReq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> xoaGiaoVien(@PathVariable Long id) {
        giaoVienService.xoaGiaoVien(id);
        return ResponseEntity.ok().build();
    }
}
