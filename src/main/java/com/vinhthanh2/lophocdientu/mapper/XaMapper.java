package com.vinhthanh2.lophocdientu.mapper;

import com.vinhthanh2.lophocdientu.dto.req.XaReq;
import com.vinhthanh2.lophocdientu.dto.res.XaRes;
import com.vinhthanh2.lophocdientu.dto.sql.XaPro;
import com.vinhthanh2.lophocdientu.entity.Xa;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {XaMapper.class}
)
public interface XaMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Xa fromDto(XaReq xaReq);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    XaRes toDto(Xa xa);

    @Mapping(source = "outId", target = "id")
    @Mapping(source = "tenTinh", target = "tinh.ten")
    @Mapping(source = "tinhId", target = "tinh.id")
    Xa fromPro(XaPro dto);

}
