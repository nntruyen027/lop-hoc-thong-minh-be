package com.vinhthanh2.lophocdientu.dto.res;

import com.vinhthanh2.lophocdientu.entity.Lop;
import lombok.Data;

import java.util.List;

@Data
public class TeacherRes {
    private Long id;

    private String username;

    private String avatar;

    private String hoTen;

    private String ngaySinh;
    private Boolean laNam;

    // Giáo viên tạo nhiều lớp
    private List<Lop> dsLopTao;

    // Dành cho giáo viên
    private String boMon;
    private String chucVu;
}
