package com.vinhthanh2.lophocdientu.dto.res;

import lombok.Data;

@Data
public class TeacherRes {
    private Long id;

    private String username;

    private String avatar;

    private String hoTen;

    private String ngaySinh;
    private Boolean laNam;


    // Dành cho giáo viên
    private String boMon;
    private String chucVu;
}
