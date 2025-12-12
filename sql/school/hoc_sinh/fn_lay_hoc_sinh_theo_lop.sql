DROP FUNCTION IF EXISTS school.fn_lay_hoc_sinh_theo_lop;


CREATE OR REPLACE FUNCTION school.fn_lay_hoc_sinh_theo_lop(
    p_lop_id BIGINT,
    p_search VARCHAR(500),
    p_offset INT DEFAULT 0,
    p_limit INT DEFAULT 10
)
    RETURNS TABLE
            (
                out_id                BIGINT,
                username              VARCHAR(120),
                avatar                TEXT,
                role_name             VARCHAR(30),
                ho_ten                TEXT,
                xa_id                 BIGINT,
                ten_xa                VARCHAR(120),
                tinh_id               BIGINT,
                ten_tinh              VARCHAR(120),
                dia_chi_chi_tiet      VARCHAR(500),
                ngay_sinh             VARCHAR(50),
                la_nam                BOOLEAN,
                so_thich              TEXT,
                lop_id                BIGINT,
                ten_lop               VARCHAR(120),
                truong_id             BIGINT,
                ten_truong            VARCHAR(120),
                mon_hoc_yeu_thich     TEXT,
                diem_manh             TEXT,
                diem_yeu              TEXT,
                nghe_nghiep_mong_muon TEXT,
                nhan_xet_giao_vien    TEXT,
                GHI_CHU               TEXT,
                realistic_score       INT,
                investigative_score   INT,
                artistic_score        INT,
                social_score          INT,
                enterprising_score    INT,
                conventional_score    INT,
                assessment_result     VARCHAR(500),
                ten_cha               TEXT,
                ns_cha                VARCHAR(50),
                sdt_cha               VARCHAR(20),
                ten_me                TEXT,
                ns_me                 VARCHAR(50),
                sdt_me                VARCHAR(20),
                ten_ph_khac           TEXT,
                ns_ph_khac            VARCHAR(50),
                sdt_ph_khac           VARCHAR(20)
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT u.id   AS out_id,
               u.username,
               u.avatar,
               u.role AS role_name,
               u.ho_ten,
               u.xa_id,
               x.ten  as ten_xa,
               ti.id  as tinh_id,
               ti.ten as ten_tinh,
               u.dia_chi_chi_tiet,
               u.ngay_sinh,
               u.la_nam,
               u.so_thich,
               u.lop_id,
               l.ten  as ten_lop,
               l.truong_id,
               t.ten  as ten_truong,
               u.mon_hoc_yeu_thich,
               u.diem_manh,
               u.diem_yeu,
               u.nghe_nghiep_mong_muon,
               u.nhan_xet_giao_vien,
               u.GHI_CHU,
               u.realistic_score,
               u.investigative_score,
               u.artistic_score,
               u.social_score,
               u.enterprising_score,
               u.conventional_score,
               u.assessment_result,
               u.ten_cha,
               u.ns_cha,
               u.sdt_cha,
               u.ten_me,
               u.ns_me,
               u.sdt_me,
               u.ten_ph_khac,
               u.ns_ph_khac,
               u.sdt_ph_khac
        FROM auth.users u
                 LEFT JOIN dm_chung.xa x ON x.id = u.xa_id
                 LEFT JOIN dm_chung.tinh ti ON ti.id = x.tinh_id
                 LEFT JOIN school.lop l on l.id = u.lop_id
                 LEFT JOIN school.truong t ON t.id = l.truong_id
        WHERE u.role = 'STUDENT'
          AND u.lop_id = p_lop_id
          AND (
            p_search IS NULL OR p_search = ''
                OR unaccent(lower(u.ho_ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
                OR unaccent(lower(u.username)) LIKE '%' || unaccent(lower(p_search)) || '%'
            )
        ORDER BY u.ho_ten
        OFFSET p_offset LIMIT p_limit;
END;

$$ LANGUAGE plpgsql;

