package com.vinhthanh2.lophocdientu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "nhom_cau_hoi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhomCauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ten;

    @Column
    private String hinhAnh;

    @OneToMany(mappedBy = "nhom", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CauHoi> dsCauHoi;
}
