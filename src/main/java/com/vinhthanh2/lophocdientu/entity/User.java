package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 120)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String avatar;

    @Column(nullable = false, length = 30)
    private String role; // STUDENT, TEACHER, ADMIN

    @Column(nullable = false)
    private String hoTen;

    @Transient
    private Xa xa;

    @Column
    private String diaChiChiTiet;


    // === Thông tin thêm ===
    @Column
    private String ngaySinh;
    @Column
    private Boolean laNam;
    @Column
    private String soThich;
    @Column
    private String monHocYeuThich;
    @Column
    private String diemManh;
    @Column
    private String diemYeu;
    @Column
    private String ngheNghiepMongMuon;
    @Column
    private String nhanXetGiaoVien;
    @Column
    private String ghiChu;

    @Column
    private Integer realisticScore;
    @Column
    private Integer investigativeScore;
    @Column
    private Integer artisticScore;
    @Column
    private Integer socialScore;
    @Column
    private Integer enterprisingScore;
    @Column
    private Integer conventionalScore;

    @Column(length = 500)
    private String assessmentResult;

    // Thông tin phụ huynh
    @Column
    private String tenCha;
    @Column
    private String nsCha;
    @Column
    private String sdtCha;
    @Column
    private String tenMe;
    @Column
    private String nsMe;
    @Column
    private String sdtMe;
    @Column
    private String tenPhKhac;
    @Column
    private String nsPhKhac;
    @Column
    private String sdtPhKhac;

    // Dành cho giáo viên
    @Column
    private String boMon;
    @Column
    private String chucVu;
}
