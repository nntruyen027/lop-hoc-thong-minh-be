DROP FUNCTION IF EXISTS school.fn_dem_hoc_sinh_theo_lop;

CREATE OR REPLACE FUNCTION school.fn_dem_hoc_sinh_theo_lop(
    p_lop_id BIGINT,
    p_search VARCHAR(500)
)
    RETURNS BIGINT
AS
$$
DECLARE
    total BIGINT;
BEGIN
    SELECT COUNT(DISTINCT u.id)
    INTO total
    FROM auth.users u
    WHERE u.role = 'STUDENT'
      AND u.lop_id = p_lop_id
      AND (
        p_search IS NULL OR p_search = ''
            OR unaccent(lower(u.ho_ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
            OR unaccent(lower(u.username)) LIKE '%' || unaccent(lower(p_search)) || '%'
        );

    RETURN total;
END;
$$ LANGUAGE plpgsql;