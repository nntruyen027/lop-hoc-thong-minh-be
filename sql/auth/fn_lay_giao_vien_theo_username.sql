DROP FUNCTION IF EXISTS auth.fn_lay_giao_vien_theo_username;

CREATE OR REPLACE FUNCTION auth.fn_lay_giao_vien_theo_username
(
    p_username VARCHAR
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
	IF NOT EXISTS (select 1 from auth.users u where u.username = p_username) THEN
		RAISE EXCEPTION 'Giáo viên với tên đăng nhập: % không tồn tại.', p_usename;
	END IF;

    RETURN QUERY
    SELECT u.id as out_id,
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
    WHERE u.username = p_username
      AND u.role = 'TEACHER'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
