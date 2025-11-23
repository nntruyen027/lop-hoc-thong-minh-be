package com.vinhthanh2.lophocdientu.service;

import com.vinhthanh2.lophocdientu.dto.res.PageResponse;
import com.vinhthanh2.lophocdientu.dto.res.TeacherRes;
import com.vinhthanh2.lophocdientu.dto.res.TruongRes;
import com.vinhthanh2.lophocdientu.mapper.TruongMapper;
import com.vinhthanh2.lophocdientu.repository.TruongRepo;

import java.util.List;

public class TruongService {
    private final TruongRepo truongRepo;
    private final TruongMapper truongMapper;

    public PageResponse<TruongRes> layDsGiaoVien(String search, int page, int size) {
        List<TruongRes> teacherResList = truongRepo
                .layTruong(search, page, size)
                .stream()
                .map(truongMapper::toDto)
                .toList();

        long totalElements = truongRepo.demTatCaTruong(search);

        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PageResponse.<TeacherRes>builder()
                .data(teacherResList)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }
}
