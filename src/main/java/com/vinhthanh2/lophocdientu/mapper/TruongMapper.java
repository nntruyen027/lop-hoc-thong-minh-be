package com.vinhthanh2.lophocdientu.mapper;

import com.vinhthanh2.lophocdientu.dto.req.TruongReq;
import com.vinhthanh2.lophocdientu.dto.res.TruongRes;
import com.vinhthanh2.lophocdientu.dto.sql.TruongPro;
import com.vinhthanh2.lophocdientu.entity.Truong;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TruongMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Truong fromDto(TruongReq truongReq);
    
    TruongRes toDto(Truong truong);

    @Mapping(source = "outId", target = "id")
    Truong fromTruongPro(TruongPro dto);
}
