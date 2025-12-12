DROP FUNCTION IF EXISTS school.fn_sua_hoc_sinh;

CREATE OR REPLACE FUNCTION school.fn_sua_hoc_sinh(
    p_id BIGINT,
    p_username VARCHAR(120),
    p_password VARCHAR(200),
    p_avatar TEXT,
    p_role VARCHAR(30),
    p_ho_ten TEXT,
    p_lop_id BIGINT,
    p_ngay_sinh VARCHAR(50),
    p_la_nam BOOLEAN,
    p_so_thich TEXT,
    p_mon_hoc_yeu_thich TEXT,
    p_diem_manh TEXT,
    p_diem_yeu TEXT,
    p_nghe_nghiep_mong_muon TEXT,
    p_nhan_xet_giao_vien TEXT,
    p_ghi_chu TEXT,
    p_realistic_score INT,
    p_investigative_score INT,
    p_artistic_score INT,
    p_social_score INT,
    p_enterprising_score INT,
    p_conventional_score INT,
    p_assessment_result VARCHAR(500),
    p_ten_cha TEXT,
    p_ns_cha VARCHAR(50),
    p_sdt_cha VARCHAR(20),
    p_ten_me TEXT,
    p_ns_me VARCHAR(50),
    p_sdt_me VARCHAR(20),
    p_ten_ph_khac TEXT,
    p_ns_ph_khac VARCHAR(50),
    p_sdt_ph_khac VARCHAR(20),
    p_xa_id BIGINT
)
    RETURNS BIGINT
AS
$$
BEGIN
    IF EXISTS(SELECT 1 FROM auth.users WHERE username = p_username) THEN
        RAISE EXCEPTION 'Người dùng với username % đã tồn tại.', p_username;
    end if;

    IF NOT EXISTS(SELECT 1 FROM school.lop WHERE id = p_lop_id) THEN
        RAISE EXCEPTION 'Lớp học với id % không tồn tại.', p_lop_id;
    END IF;

    IF NOT EXISTS(SELECT 1 FROM dm_chung.xa WHERE id = p_xa_id) THEN
        RAISE EXCEPTION 'Xã với id % không tồn tại.', p_xa_id;
    end if;

    UPDATE auth.users
    SET username              = p_username,
        password              = p_password,
        avatar                = p_avatar,
        role                  = p_role,
        ho_ten                = p_ho_ten,
        lop_id                = p_lop_id,
        ngay_sinh             = p_ngay_sinh,
        la_nam                = p_la_nam,
        so_thich              = p_so_thich,
        mon_hoc_yeu_thich     = p_mon_hoc_yeu_thich,
        diem_manh             = p_diem_manh,
        diem_yeu              = p_diem_yeu,
        nghe_nghiep_mong_muon = p_nghe_nghiep_mong_muon,
        nhan_xet_giao_vien    = p_nhan_xet_giao_vien,
        ghi_chu               = p_ghi_chu,
        realistic_score       = p_realistic_score,
        investigative_score   = p_investigative_score,
        artistic_score        = p_artistic_score,
        social_score          = p_social_score,
        enterprising_score    = p_enterprising_score,
        conventional_score    = p_conventional_score,
        assessment_result     = p_assessment_result,
        ten_cha               = p_ten_cha,
        ns_cha                = p_ns_cha,
        sdt_cha               = p_sdt_cha,
        ten_me                = p_ten_me,
        ns_me                 = p_ns_me,
        sdt_me                = p_sdt_me,
        ten_ph_khac           = p_ten_ph_khac,
        ns_ph_khac            = p_ns_ph_khac,
        sdt_ph_khac           = p_sdt_ph_khac,
        xa_id                 = p_xa_id
    WHERE id = p_id;

    RETURN p_id;
END;
$$ LANGUAGE plpgsql;
