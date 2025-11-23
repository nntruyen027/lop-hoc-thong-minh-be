package com.vinhthanh2.lophocdientu.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiaoVienPro {
    private Long outId;

    private String username;

    private String hoTen;

    private String avatar;

    private String ngaySinh;
    private Boolean laNam;
    // Dành cho giáo viên
    private String boMon;
    private String chucVu;
}
