package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bai_lam", schema = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiLam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lan_thi_id", nullable = false)
    private LanThi lanThi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cau_hoi_id", nullable = false)
    private CauHoi cauHoi;

    private String dapAnHocSinh;
    private Boolean dung;
    private Double diem;

}
