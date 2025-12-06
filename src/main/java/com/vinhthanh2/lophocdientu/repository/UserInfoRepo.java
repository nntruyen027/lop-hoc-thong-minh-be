package com.vinhthanh2.lophocdientu.repository;

import com.vinhthanh2.lophocdientu.dto.sql.AdminPro;
import com.vinhthanh2.lophocdientu.dto.sql.GiaoVienPro;
import com.vinhthanh2.lophocdientu.dto.sql.HocSinhPro;
import com.vinhthanh2.lophocdientu.entity.User;
import com.vinhthanh2.lophocdientu.mapper.HocSinhMapper;
import com.vinhthanh2.lophocdientu.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class UserInfoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserMapper userMapper;

    private HocSinhMapper hocSinhMapper;

    public User layGiaoVienTheoUsername(String username) {
        String sql = """
                    SELECT * FROM auth.fn_lay_giao_vien_theo_username(:p_username)
                """;

        return userMapper.fromGiaoVienPro((GiaoVienPro) entityManager.createNativeQuery(sql, GiaoVienPro.class)
                .setParameter("p_username", username)
                .getSingleResult());
    }

    public User layQuanTriTheoUsername(String username) {
        String sql = """
                    SELECT * FROM auth.fn_lay_quan_tri_theo_username(:p_username)
                """;

        return userMapper.fromAdminPro((AdminPro) entityManager.createNativeQuery(sql, AdminPro.class)
                .setParameter("p_username", username)
                .getSingleResult());
    }

    public User layHocSinhTheoUsername(String username) {
        String sql = """
                    SELECT * FROM auth.fn_lay_hoc_sinh_theo_username(:p_username)
                """;

        return hocSinhMapper.fromHocSinhPro((HocSinhPro) entityManager.createNativeQuery(sql, HocSinhPro.class)
                .setParameter("p_username", username)
                .getSingleResult());
    }
}
