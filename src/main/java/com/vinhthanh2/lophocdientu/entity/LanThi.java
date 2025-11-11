package com.vinhthanh2.lophocdientu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lan_thi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanThi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Học sinh nào làm bài này
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_sinh_id", nullable = false)
    private HocSinh hocSinh;

    // Đề nào được dùng trong lần thi này (mỗi học sinh có thể có đề riêng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "de_thi_id", nullable = false)
    private DeThi deThi;

    private LocalDateTime batDau;
    private LocalDateTime ketThuc;

    private Double tongDiem;
    private String nhanXet;

    @OneToMany(mappedBy = "lanThi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BaiLam> baiLams;
}
