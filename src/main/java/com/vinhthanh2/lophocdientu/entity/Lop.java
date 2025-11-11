package com.vinhthanh2.lophocdientu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String ten;

    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truong_id")
    @JsonBackReference
    private Truong truong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "giao_vien_id")
    @JsonBackReference
    private User giaoVien;

    @OneToMany(mappedBy = "lop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HocSinh> dsHocSinh;
}
