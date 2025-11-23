package com.vinhthanh2.lophocdientu.dto.req;

import lombok.Data;

@Data
public class UpdatePassReq {
    private String oldPass;
    private String newPass;
    private String repeatNewPass;
}
