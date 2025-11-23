package com.vinhthanh2.lophocdientu.controller.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quantri/truong")
@Getter
@Setter
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminTruongController {
    final private TruongService truongService;

    @GetMapping()
    public ResponseEntity<?> layDsTruong(@RequestParam(required = false, defaultValue = "") String search,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(giaoVienService.layDsGiaoVien(search, page, size));
    }
}
