package com.vinhthanh2.lophocdientu.repository;

import com.vinhthanh2.lophocdientu.dto.req.TeacherRegisterReq;
import com.vinhthanh2.lophocdientu.dto.req.UpdateTeacherReq;
import com.vinhthanh2.lophocdientu.dto.sql.GiaoVienPro;
import com.vinhthanh2.lophocdientu.entity.User;
import com.vinhthanh2.lophocdientu.exception.AppException;
import com.vinhthanh2.lophocdientu.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class GiaoVienRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserMapper userMapper;

    @SuppressWarnings("unchecked")
    public List<User> layTatCaGiaoVien(String search, int page, int size) {
        int offset = (page - 1) * size;

        String sql = """
                SELECT * FROM school.fn_lay_tat_ca_giao_vien(
                    :p_search, :p_offset, :p_limit
                )
                """;

        List<GiaoVienPro> gvPro = entityManager.createNativeQuery(sql, GiaoVienPro.class)
                .setParameter("p_search", search)
                .setParameter("p_offset", offset)
                .setParameter("p_limit", size)
                .getResultList().stream().toList();

        return gvPro.stream().map(userMapper::fromGiaoVienPro).toList();
    }

    // ============================================================
    // LẤY GIÁO VIÊN THEO TRƯỜNG
    // ============================================================
    @SuppressWarnings("unchecked")
    public List<User> layGiaoVienTheoTruong(Long truongId, String search, int page, int size) {
        int offset = (page - 1) * size;

        String sql = """
                    SELECT * FROM school.fn_lay_giao_vien_theo_truong(
                        :p_truong_id::bigint, :p_search, :p_offset, :p_limit
                    )
                """;

        return entityManager.createNativeQuery(sql, User.class)
                .setParameter("p_truong_id::bigint", truongId)
                .setParameter("p_search", search)
                .setParameter("p_offset", offset)
                .setParameter("p_limit", size)
                .getResultList();
    }

    public Long demTatCaGiaoVien(String search) {
        String sql = """
                    SELECT school.fn_dem_tat_ca_giao_vien(:p_search)
                """;

        Object result = entityManager.createNativeQuery(sql)
                .setParameter("p_search", search)
                .getSingleResult();

        if (result == null) return 0L;
        if (result instanceof Number num) return num.longValue();

        return Long.parseLong(result.toString());
    }

    // ============================================================
    // ĐẾM GIÁO VIÊN THEO TRƯỜNG
    // ============================================================
    public Long demGiaoVienTheoTruong(Long truongId, String search) {
        String sql = """
                    SELECT school.fn_dem_giao_vien_theo_truong(:p_truong_id::bigint, :p_search)
                """;

        Object result = entityManager.createNativeQuery(sql)
                .setParameter("p_truong_id::bigint", truongId)
                .setParameter("p_search", search)
                .getSingleResult();

        if (result == null) return 0L;
        if (result instanceof Number num) return num.longValue();

        return Long.parseLong(result.toString());
    }

    // ============================================================
    // LẤY GIÁO VIÊN THEO USERNAME
    // ============================================================
    @SuppressWarnings("unchecked")
    public User layGiaoVienTheoUsername(String username) {
        String sql = """
                    SELECT * FROM school.fn_lay_giao_vien_theo_username(:p_username)
                """;

        List<User> list = entityManager.createNativeQuery(sql, User.class)
                .setParameter("p_username", username)
                .getResultList();

        if (list.isEmpty()) {
            throw new AppException("Không tìm thấy giáo viên có username: " + username, "USER_NOT_FOUND");
        }

        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public User layGiaoVienTheoId(Long id) {
        String sql = """
                    SELECT * FROM school.fn_lay_giao_vien_theo_id(:p_id::bigint)
                """;

        List<User> list = entityManager.createNativeQuery(sql, GiaoVienPro.class)
                .setParameter("p_id::bigint", id)
                .getResultList();

        if (list.isEmpty()) {
            throw new AppException("Không tìm thấy giáo viên có id: " + id, "USER_NOT_FOUND");
        }

        return list.get(0);
    }

    // ============================================================
    // TẠO GIÁO VIÊN
    // ============================================================
    @Transactional
    public User taoGiaoVien(TeacherRegisterReq req) {

        String sql = """
                    SELECT * FROM school.fn_tao_giao_vien(
                        :p_username,
                        :p_password,
                        :p_avatar,
                        :p_ho_ten,
                        :p_ngay_sinh,
                        :p_la_nam,
                        :p_bo_mon,
                        :p_chuc_vu
                    )
                """;


        return userMapper.fromGiaoVienPro((GiaoVienPro) entityManager.createNativeQuery(sql, GiaoVienPro.class)
                .setParameter("p_username", req.getUsername())
                .setParameter("p_password", req.getPassword())
                .setParameter("p_avatar", req.getAvatar())
                .setParameter("p_ho_ten", req.getHoTen())
                .setParameter("p_ngay_sinh", req.getNgaySinh())
                .setParameter("p_la_nam", req.getLaNam())
                .setParameter("p_bo_mon", req.getBoMon())
                .setParameter("p_chuc_vu", req.getChucVu())
                .getSingleResult());
    }

    // ============================================================
    // SỬA GIÁO VIÊN
    // ============================================================
    @Transactional
    public User suaGiaoVien(Long id, UpdateTeacherReq req) {

        String sql = """
                    SELECT * FROM school.fn_sua_giao_vien(
                        :p_id::bigint,
                        :p_avatar,
                        :p_ho_ten,
                        :p_ngay_sinh,
                        :p_la_nam,
                        :p_bo_mon,
                        :p_chuc_vu
                    )
                """;

        return (User) userMapper.fromGiaoVienPro((GiaoVienPro) entityManager.createNativeQuery(sql, GiaoVienPro.class)
                .setParameter("p_id::bigint", id)
                .setParameter("p_avatar", req.getAvatar())
                .setParameter("p_ho_ten", req.getHoTen())
                .setParameter("p_ngay_sinh", req.getNgaySinh())
                .setParameter("p_la_nam", req.getLaNam())
                .setParameter("p_bo_mon", req.getBoMon())
                .setParameter("p_chuc_vu", req.getChucVu())
                .getSingleResult());
    }

    // ============================================================
    // XÓA GIÁO VIÊN
    // ============================================================
    @Transactional
    public boolean xoaGiaoVien(Long id) {
        String sql = """
                    SELECT school.fn_xoa_giao_vien(:p_id::bigint)
                """;

        Object result = entityManager.createNativeQuery(sql)
                .setParameter("p_id::bigint", id)
                .getSingleResult();

        if (result instanceof Boolean b) return b;
        return Boolean.parseBoolean(result.toString());
    }
}
