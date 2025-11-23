package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "de_thi_cau_hoi", schema = "exam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeThiCauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private Integer thuTu;
    private Double diem;
}
