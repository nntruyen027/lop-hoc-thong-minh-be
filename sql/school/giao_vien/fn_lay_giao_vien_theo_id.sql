DROP FUNCTION IF EXISTS school.fn_lay_giao_vien_theo_id;

CREATE OR REPLACE FUNCTION school.fn_lay_giao_vien_theo_id
(
    p_id BIGINT
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
	dia_chi_chi_tiet VARCHAR(500),
	xa_id BIGINT,
	ten_xa VARCHAR(120),
	tinh_id BIGINT,
	ten_tinh VARCHAR(120),
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
		   x.id as xa_id,
		   x.ten as ten_xa,
		   t.id as tinh_id,
		   t.ten as ten_tinh,
		   u.dia_chi_chi_tiet,
		   u.role
    FROM auth.users u
	LEFT JOIN dm_chung.xa x ON x.id = u.xa_id
	LEFT JOIN dm_chung.tinh t ON t.id = x.tinh_id
    WHERE u.id = p_id
      AND u.role = 'TEACHER'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;