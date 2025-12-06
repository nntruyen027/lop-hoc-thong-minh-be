package com.vinhthanh2.lophocdientu.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HocSinhPro {

    private Long outId;

    private String username;

    private String avatar;

    private String role;

    private String hoTen;

    private Long xaId;

    private String tenXa;

    private Long tinhId;

    private String tenTinh;

    private String diaChiChiTiet;

    private String ngaySinh;

    private Boolean laNam;

    private String soThich;

    private Long lopId;

    private String tenLop;

    private Long truongId;

    private String tenTruong;

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
