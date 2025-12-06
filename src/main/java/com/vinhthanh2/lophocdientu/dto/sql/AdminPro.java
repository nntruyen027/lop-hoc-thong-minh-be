package com.vinhthanh2.lophocdientu.dto.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminPro {
    private Long outId;
    private String username;
    private String hoTen;
    private String avatar;
    private String role;
}
