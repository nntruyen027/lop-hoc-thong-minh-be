CREATE OR REPLACE FUNCTION school.fn_sua_giao_vien
(
    p_id BIGINT,
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
    username VARCHAR(50),
    ho_ten TEXT,
    avatar TEXT,
    ngay_sinh VARCHAR(50),
    la_nam BOOLEAN,
    bo_mon TEXT,
    chuc_vu TEXT
)
AS $$
BEGIN
    UPDATE auth.users
    SET
        avatar = p_avatar,
        ho_ten = p_ho_ten,
        ngay_sinh = p_ngay_sinh,
        la_nam = p_la_nam,
        bo_mon = p_bo_mon,
        chuc_vu = p_chuc_vu
    WHERE id = p_id
      AND role = 'TEACHER';

    RETURN QUERY
    SELECT u.id as out_id,
           u.username,
           u.ho_ten,
           u.avatar,
           u.ngay_sinh,
           u.la_nam,
           u.bo_mon,
           u.chuc_vu
    FROM auth.users u
    WHERE u.id = p_id
      AND u.role = 'TEACHER'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
