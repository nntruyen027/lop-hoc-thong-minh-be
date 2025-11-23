package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "de_thi", schema = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeThi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenDe;
    private String moTa;

    @OneToMany(mappedBy = "deThi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeThiCauHoi> cauHoiList;
}
