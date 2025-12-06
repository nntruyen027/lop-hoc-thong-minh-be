DROP FUNCTION IF EXISTS auth.fn_lay_quan_tri_theo_username;

CREATE OR REPLACE FUNCTION auth.fn_lay_quan_tri_theo_username
(
    p_username VARCHAR
)
RETURNS TABLE
(
    out_id BIGINT,
    username VARCHAR(50),
    ho_ten TEXT,
    avatar TEXT,
    role VARCHAR(30)
)
AS $$
BEGIN
	IF NOT EXISTS (select 1 from auth.users u where u.username = p_username) THEN
		RAISE EXCEPTION 'Quản trị viên với tên đăng nhập: % không tồn tại.', p_usename;
	END IF;

    RETURN QUERY
    SELECT u.id as out_id,
           u.username,
           u.ho_ten,
           u.avatar,
           u.role
    FROM auth.users u
    WHERE u.username = p_username
      AND u.role = 'ADMIN'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
