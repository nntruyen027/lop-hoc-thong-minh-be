package com.vinhthanh2.lophocdientu.dto.req;

import com.vinhthanh2.lophocdientu.entity.Truong;
import com.vinhthanh2.lophocdientu.entity.User;

import java.util.List;

public class LopReq {
    private Long id;

    private String ten;

    private String hinhAnh;

    private Truong truong;

    private User giaoVien;

    private List<User> dsHocSinh;
}
