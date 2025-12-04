package com.vinhthanh2.lophocdientu.dto.res;

import lombok.Data;

@Data
public class XaRes {
    private Long id;

    private String ten;

    private String ghiChu;

    private TinhRes tinh;
}
