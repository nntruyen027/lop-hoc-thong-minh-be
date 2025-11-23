package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cau_hoi", schema = "exam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cauHoi;

    @Column
    private String cauA;

    @Column
    private String cauB;

    @Column
    private String cauC;

    @Column
    private String cauD;

    @Column
    private String tuLuan;

    @Column
    private String dapAn;

    @Column
    private String diemMacDinh;
}
