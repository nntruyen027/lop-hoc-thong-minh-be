package com.vinhthanh2.lophocdientu.service;

import com.vinhthanh2.lophocdientu.dto.req.StudentRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdateStudentReq;
import com.vinhthanh2.lophocdientu.dto.res.PageResponse;
import com.vinhthanh2.lophocdientu.dto.res.StudentRes;
import com.vinhthanh2.lophocdientu.entity.User;
import com.vinhthanh2.lophocdientu.mapper.UserMapper;
import com.vinhthanh2.lophocdientu.repository.HocSinhRepo;
import com.vinhthanh2.lophocdientu.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HocSinhService {
    private final HocSinhRepo hocSinhRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public PageResponse<StudentRes> layHocSinhTheoLop(Long lopId, String search, int page, int size) {
        List<StudentRes> teacherResList = hocSinhRepo
                .layHocSinhTheoLop(lopId, search, page, size)
                .stream()
                .map(userMapper::toStudentDto)
                .toList();

        long totalElements = hocSinhRepo.demHocSinhTheoLop(lopId, search);

        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PageResponse.<StudentRes>builder()
                .data(teacherResList)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }

    public StudentRes layHocSinhTheoId(Long id) {
        return userMapper.toStudentDto(hocSinhRepo.layHocSinhTheoId(id));
    }

    public StudentRes dangKyHocSinh(StudentRegisterReq req) {
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        return userMapper.toStudentDto(hocSinhRepo.taoHocSinh(req));
    }

    public StudentRes suaHocSinh(Long id, UpdateStudentReq req) {
        return userMapper.toStudentDto(hocSinhRepo.suaHocSinh(req));
    }

    public StudentRes suaThongTinCaNhan(UpdateStudentReq updateStudentReq) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() ||
                "anonymousUser".equals(auth.getPrincipal())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        User user = userRepo.findByUsername(auth.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        return suaHocSinh(user.getId(), updateStudentReq);
    }

    public void xoaHocSinh(Long id) {
        hocSinhRepo.xoaHocSinh(id);
    }


}
