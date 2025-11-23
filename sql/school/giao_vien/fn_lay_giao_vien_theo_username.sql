CREATE OR REPLACE FUNCTION school.fn_lay_giao_vien_theo_username
(
    p_username VARCHAR
)
RETURNS TABLE
(
    id BIGINT,
    username VARCHAR,
    ho_ten TEXT,
    avatar TEXT,
    ngay_sinh TEXT,
    la_nam BOOLEAN,
    bo_mon TEXT,
    chuc_vu TEXT
)
AS $$
BEGIN
    RETURN QUERY
    SELECT u.id,
           u.username,
           u.ho_ten,
           u.avatar,
           u.ngay_sinh,
           u.la_nam,
           u.bo_mon,
           u.chuc_vu
    FROM auth.users u
    WHERE u.username = p_username
      AND u.role = 'TEACHER'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
