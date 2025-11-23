CREATE OR REPLACE FUNCTION school.fn_tao_giao_vien
(
    p_username VARCHAR(120),
    p_password TEXT,
    p_avatar TEXT,
    p_ho_ten TEXT,
    p_ngay_sinh VARCHAR(50),
    p_la_nam BOOLEAN,
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
    chuc_vu TEXT
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
    INSERT INTO auth.users (
        username,
        password,
        avatar,
        role,
        ho_ten,
        ngay_sinh,
        la_nam,
        bo_mon,
        chuc_vu
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
        p_chuc_vu
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
        u.chuc_vu
    FROM auth.users u
    WHERE u.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
