package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "de_thi_cau_hoi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeThiCauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "de_thi_id")
    private DeThi deThi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cau_hoi_id")
    private CauHoi cauHoi;

    private Integer thuTu;
    private Double diem;
}
