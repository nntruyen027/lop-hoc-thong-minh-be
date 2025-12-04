package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "xa", schema = "dm_chung")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Xa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String ten;

    @Column
    private String ghiChu;

    @Transient
    private Tinh tinh;
}
