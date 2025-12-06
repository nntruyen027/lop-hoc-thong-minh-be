DROP FUNCTION IF EXISTS school.fn_tao_giao_vien;

CREATE OR REPLACE FUNCTION school.fn_tao_giao_vien
(
    p_username VARCHAR(120),
    p_password TEXT,
    p_avatar TEXT,
    p_ho_ten TEXT,
    p_ngay_sinh VARCHAR(50),
    p_la_nam BOOLEAN,
    p_xa_id BIGINT,
    p_dia_chi_chi_tiet VARCHAR(500),
    p_bo_mon TEXT,
    p_chuc_vu TEXT
)
RETURNS TABLE
(
    out_id BIGINT,
    username VARCHAR(120),
    ho_ten TEXT,
    avatar TEXT,
    ngay_sinh VARCHAR(50),
    la_nam BOOLEAN,
    bo_mon TEXT,
    chuc_vu TEXT,
	dia_chi_chi_tiet VARCHAR(500),
	xa_id BIGINT,
    ten_xa VARCHAR(120),
	tinh_id BIGINT,
    ten_tinh VARCHAR(120),
	role VARCHAR(30)
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
	IF NOT EXISTS (SELECT 1 FROM dm_chung.xa x WHERE x.id = p_xa_id) THEN
		RAISE EXCEPTION 'Xã với id % không tồn tại', p_xa_id;
	END IF;

    INSERT INTO auth.users (
        username,
        password,
        avatar,
        role,
        ho_ten,
        ngay_sinh,
        la_nam,
        bo_mon,
        chuc_vu,
        xa_id,
        dia_chi_chi_tiet
    )
    VALUES (
        p_username,
        p_password,
        p_avatar,
        'TEACHER',
        p_ho_ten,
        p_ngay_sinh,
        p_la_nam,
        p_bo_mon,
        p_chuc_vu,
        p_xa_id,
        p_dia_chi_chi_tiet
    )
    RETURNING id INTO new_id;

    RETURN QUERY
    SELECT
        u.id AS out_id,
        u.username,
        u.ho_ten,
        u.avatar,
        u.ngay_sinh,
        u.la_nam,
        u.bo_mon,
        u.chuc_vu,
        u.dia_chi_chi_tiet,
        x.id as xa_id,
        x.ten as ten_xa,
        t.id as tinh_id,
        t.ten as ten_tinh,
		u.role
    FROM auth.users u
    LEFT JOIN dm_chung.xa x ON x.id = u.xa_id
    LEFT JOIN dm_chung.tinh t ON t.id = x.tinh_id
    WHERE u.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
