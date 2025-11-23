package com.vinhthanh2.lophocdientu.dto.req;

import lombok.Data;

@Data
public class TeacherRegisterReq {
    private String username;

    private String password;

    private String avatar;

    private String hoTen;

    private String ngaySinh;
    private Boolean laNam;

    // Dành cho giáo viên
    private String boMon;
    private String chucVu;
}
