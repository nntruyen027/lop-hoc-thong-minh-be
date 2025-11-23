package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "lan_thi", schema = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime batDau;
    private LocalDateTime ketThuc;

    private Double tongDiem;
    private String nhanXet;
}
