package com.vinhthanh2.lophocdientu.dto.req;

import com.vinhthanh2.lophocdientu.entity.Lop;
import lombok.Data;

@Data
public class StudentRegisterReq {
    private String username;

    private String password;

    private String avatar;

    private String role; // STUDENT, TEACHER, ADMIN

    private String hoTen;

    // Học sinh thuộc lớp nào
    private Lop lop;


    // === Thông tin thêm ===
    private String ngaySinh;
    private Boolean laNam;
    private String soThich;
    private String monHocYeuThich;
    private String diemManh;
    private String diemYeu;
    private String ngheNghiepMongMuon;
    private String nhanXetGiaoVien;
    private String ghiChu;

    private Integer realisticScore;
    private Integer investigativeScore;
    private Integer artisticScore;
    private Integer socialScore;
    private Integer enterprisingScore;
    private Integer conventionalScore;
    private String assessmentResult;

    // Thông tin phụ huynh
    private String tenCha;
    private String nsCha;
    private String sdtCha;
    private String tenMe;
    private String nsMe;
    private String sdtMe;
    private String tenPhKhac;
    private String nsPhKhac;
    private String sdtPhKhac;
}
