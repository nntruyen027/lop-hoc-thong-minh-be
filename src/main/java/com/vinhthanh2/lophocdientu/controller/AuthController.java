package com.vinhthanh2.lophocdientu.controller;

import com.vinhthanh2.lophocdientu.dto.req.TeacherRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdatePassReq;
import com.vinhthanh2.lophocdientu.mapper.UserMapper;
import com.vinhthanh2.lophocdientu.repository.UserRepo;
import com.vinhthanh2.lophocdientu.service.AuthService;
import com.vinhthanh2.lophocdientu.service.GiaoVienService;
import com.vinhthanh2.lophocdientu.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.get("username"), request.get("password"))
        );

        String token = jwtService.generateToken(request.get("username"));
        return Map.of("token", token);
    }

    @PostMapping("/dang-ky-giao-vien")
    public ResponseEntity<?> dangKyGiaoVien(@RequestBody TeacherRegisterReq req) {
        return ResponseEntity.ok((giaoVienService.dangKyGiaoVien(req)));
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
