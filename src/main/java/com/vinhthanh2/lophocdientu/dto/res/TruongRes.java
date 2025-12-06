package com.vinhthanh2.lophocdientu.dto.res;

import lombok.Data;

@Data
public class TruongRes {
    private Long id;

    private String ten;

    private XaRes xa;

    private String diaChiChiTiet;

    private String hinhAnh;

    private String logo;
}
