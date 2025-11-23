package com.vinhthanh2.lophocdientu.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TruongPro {
    private Long outId;

    private String ten;

    private String diaChi;

    private String hinhAnh;

    private String logo;
}
