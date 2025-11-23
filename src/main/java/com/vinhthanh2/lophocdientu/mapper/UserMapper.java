package com.vinhthanh2.lophocdientu.mapper;

import com.vinhthanh2.lophocdientu.dto.req.StudentRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.TeacherRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdateStudentReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdateTeacherReq;
import com.vinhthanh2.lophocdientu.dto.res.StudentRes;
import com.vinhthanh2.lophocdientu.dto.res.TeacherRes;
import com.vinhthanh2.lophocdientu.dto.sql.GiaoVienPro;
import com.vinhthanh2.lophocdientu.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromStudentRegisterDto(StudentRegisterReq dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromStudentUpdateDto(UpdateStudentReq dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromTeacherRegisterDto(TeacherRegisterReq dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromTeacherUpdateDto(UpdateTeacherReq dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentRegisterReq dto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherRegisterReq dto, @MappingTarget User user);

    StudentRes toStudentDto(User user);

    TeacherRes toTeacherDto(User user);

    @Mapping(source = "outId", target = "id")
    User fromGiaoVienPro(GiaoVienPro dto);
}
