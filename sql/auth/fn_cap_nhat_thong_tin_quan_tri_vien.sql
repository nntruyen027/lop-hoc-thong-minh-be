DROP FUNCTION IF EXISTS auth.fn_cap_nhat_thong_tin_quan_tri_vien;

CREATE OR REPLACE FUNCTION auth.fn_cap_nhat_thong_tin_quan_tri_vien
(
    p_id BIGINT,
    p_avatar TEXT,
    p_ho_ten TEXT
)
RETURNS TABLE
(
    out_id BIGINT,
    username VARCHAR(50),
    ho_ten TEXT,
    avatar TEXT,
    role_name VARCHAR(30)
)
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM auth.users u
        WHERE u.id = p_id
          AND u."role" = 'ADMIN'
    ) THEN
        RAISE EXCEPTION 'Quản trị viên với id % không tồn tại', p_id;
END IF;

    -- Cập nhật thông tin
UPDATE auth.users
SET
    avatar = p_avatar,
    ho_ten = p_ho_ten
WHERE id = p_id AND "role" = 'ADMIN';

-- Trả về thông tin sau cập nhật
RETURN QUERY
SELECT
    u.id AS out_id,
    u.username,
    u.ho_ten,
    u.avatar,
    u.role AS role_name
FROM auth.users u
WHERE u.id = p_id
  AND u.role = 'ADMIN'
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
