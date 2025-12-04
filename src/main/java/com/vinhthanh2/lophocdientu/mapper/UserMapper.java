package com.vinhthanh2.lophocdientu.mapper;

import com.vinhthanh2.lophocdientu.dto.res.StudentRes;
import com.vinhthanh2.lophocdientu.dto.res.TeacherRes;
import com.vinhthanh2.lophocdientu.dto.sql.GiaoVienPro;
import com.vinhthanh2.lophocdientu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {XaMapper.class})
public interface UserMapper {

    @Mapping(source = "role", target = "role")
    StudentRes toStudentDto(User user);

    @Mapping(source = "role", target = "role")
    TeacherRes toTeacherDto(User user);

    @Mapping(source = "outId", target = "id")
    @Mapping(source = "xaId", target = "xa.id")
    @Mapping(source = "tenXa", target = "xa.ten")
    User fromGiaoVienPro(GiaoVienPro dto);
}
