package com.vinhthanh2.lophocdientu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HocSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hoTen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lop_id")
    @JsonBackReference
    private Lop lop;

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
}
