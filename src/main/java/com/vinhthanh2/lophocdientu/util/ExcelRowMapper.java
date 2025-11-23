package com.vinhthanh2.lophocdientu.util;

@FunctionalInterface
public interface ExcelRowMapper<T> {
    T mapRow(org.apache.poi.ss.usermodel.Row row) throws Exception;
}

