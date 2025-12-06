package com.vinhthanh2.lophocdientu.mapper;

import com.vinhthanh2.lophocdientu.dto.res.AdminRes;
import com.vinhthanh2.lophocdientu.dto.res.TeacherRes;
import com.vinhthanh2.lophocdientu.dto.sql.AdminPro;
import com.vinhthanh2.lophocdientu.dto.sql.GiaoVienPro;
import com.vinhthanh2.lophocdientu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {XaMapper.class, TinhMapper.class})
public interface UserMapper {


    @Mapping(source = "role", target = "role")
    TeacherRes toTeacherDto(User user);

    @Mapping(source = "role", target = "role")
    AdminRes toAdminDto(User user);

    @Mapping(source = "outId", target = "id")
    @Mapping(source = "xaId", target = "xa.id")
    @Mapping(source = "tenXa", target = "xa.ten")
    @Mapping(source = "tenTinh", target = "xa.tinh.ten")
    @Mapping(source = "tinhId", target = "xa.tinh.id")
    User fromGiaoVienPro(GiaoVienPro dto);


    @Mapping(source = "outId", target = "id")
    User fromAdminPro(AdminPro dto);
}
