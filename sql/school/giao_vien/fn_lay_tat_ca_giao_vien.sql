CREATE OR REPLACE FUNCTION school.fn_lay_tat_ca_giao_vien
(
    p_search VARCHAR(500),
    p_offset INT DEFAULT 1,
    p_limit INT DEFAULT 10
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
BEGIN
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
    WHERE u.role = 'TEACHER'
      AND (
            p_search IS NULL OR p_search = ''
            OR unaccent(lower(u.ho_ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
            OR unaccent(lower(u.username)) LIKE '%' || unaccent(lower(p_search)) || '%'
          )
    ORDER BY u.ho_ten
    OFFSET p_offset * p_limit
    LIMIT p_limit;
END;
$$ LANGUAGE plpgsql;
