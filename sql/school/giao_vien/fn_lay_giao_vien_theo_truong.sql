DROP FUNCTION IF EXISTS school.fn_lay_giao_vien_theo_truong;

CREATE OR REPLACE FUNCTION school.fn_lay_giao_vien_theo_truong
(
    p_truong_id BIGINT,
    p_search VARCHAR(500),
    p_offset INT DEFAULT 1,
    p_limit INT DEFAULT 10
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
    chuc_vu TEXT,
	ten_xa VARCHAR(120),
	ten_tinh VARCHAR(120),
	dia_chi_chi_tiet VARCHAR(500),
	xa_id BIGINT,
	tinh_id BIGINT,
	role VARCHAR(30)
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
           u.chuc_vu,
		   u.dia_chi_chi_tiet,
		   x.id as xa_id,
		   x.ten as ten_xa,
		   t.id as tinh_id,
		   t.ten as ten_tinh,
		   u.role
    FROM auth.users u
    JOIN school.lop l ON l.giao_vien_id = u.id
	LEFT JOIN dm_chung.xa x ON x.id = u.xa_id
	LEFT JOIN dm_chung.tinh t ON t.id = x.tinh_id
    WHERE u.role = 'TEACHER'
      AND l.truong_id = p_truong_id
      AND (
            p_search IS NULL OR p_search = ''
            OR unaccent(lower(u.ho_ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
            OR unaccent(lower(u.username)) LIKE '%' || unaccent(lower(p_search)) || '%'
          )
    ORDER BY u.ho_ten
    OFFSET (p_offset - 1) * p_limit
    LIMIT p_limit;
END;
$$ LANGUAGE plpgsql;


