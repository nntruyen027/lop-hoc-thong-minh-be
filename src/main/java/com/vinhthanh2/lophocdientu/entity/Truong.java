package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "truong", schema = "school")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Truong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String ten;

    @Column
    private String diaChi;

    @Column
    private String hinhAnh;

    @Column
    private String logo;
}
