package com.vinhthanh2.lophocdientu.controller;

import com.vinhthanh2.lophocdientu.dto.req.TeacherRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdatePassReq;
import com.vinhthanh2.lophocdientu.exception.AppException;
import com.vinhthanh2.lophocdientu.mapper.UserMapper;
import com.vinhthanh2.lophocdientu.repository.UserRepo;
import com.vinhthanh2.lophocdientu.service.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Getter
@Setter
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private AuthService authService;
    private final GiaoVienService giaoVienService;
    private final UserMapper userMapper;
    private final TinhService tinhService;
    private final XaService xaService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.get("username"),
                            request.get("password")
                    )
            );

            String token = jwtService.generateToken(request.get("username"));
            return Map.of("token", token);

        } catch (BadCredentialsException ex) {
            throw new AppException("BAD_CREDENTIAL", "Thông tin đăng nhập không hợp lệ");
        }
    }

    @PostMapping("/dang-ky-giao-vien")
    public ResponseEntity<?> dangKyGiaoVien(@RequestBody TeacherRegisterReq req) {
        return ResponseEntity.ok((giaoVienService.dangKyGiaoVien(req)));
    }

    @GetMapping("/tinh")
    public ResponseEntity<?> layDsTinh(@RequestParam(required = false, defaultValue = "") String search,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tinhService.layDsTinh(search, page, size));
    }

    @GetMapping("tinh/{tinhId}/xa")
    public ResponseEntity<?> layDsXa(@RequestParam(required = false, defaultValue = "") String search,
                                     @PathVariable Long tinhId,
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(xaService.layDsXa(search, tinhId, page, size));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUserDto());
    }


    @PutMapping("/doi-mat-khau")
    public ResponseEntity<?> doiMatKhau(@RequestBody UpdatePassReq updatePassReq) {
        authService.doiMatKhau(updatePassReq);
        return ResponseEntity.ok().build();
    }
}
