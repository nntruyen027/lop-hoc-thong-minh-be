CREATE OR REPLACE FUNCTION school.fn_dem_giao_vien_theo_truong
(
    p_truong_id BIGINT,
    p_search VARCHAR(500)
)
RETURNS BIGINT
AS $$
DECLARE
    total BIGINT;
BEGIN
    SELECT COUNT(DISTINCT u.id)
    INTO total
    FROM auth.users u
    JOIN school.lop l ON l.giao_vien_id = u.id
    WHERE u.role = 'TEACHER'
      AND l.truong_id = p_truong_id
      AND (
            p_search IS NULL OR p_search = ''
            OR unaccent(lower(u.ho_ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
            OR unaccent(lower(u.username)) LIKE '%' || unaccent(lower(p_search)) || '%'
          );

    RETURN total;
END;
$$ LANGUAGE plpgsql;
