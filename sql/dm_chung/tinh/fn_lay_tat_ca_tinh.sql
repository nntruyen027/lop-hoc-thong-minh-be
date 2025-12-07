DROP FUNCTION IF EXISTS dm_chung.fn_lay_tat_ca_tinh;

CREATE OR REPLACE FUNCTION dm_chung.fn_lay_tat_ca_tinh
(
    p_search VARCHAR(500),
    p_offset INT DEFAULT 1,
    p_limit INT DEFAULT 10
)
RETURNS TABLE
(
    out_id BIGINT,
    ten VARCHAR(120),
    ghi_chu TEXT
)
AS $$
BEGIN
    RETURN QUERY
    SELECT t.id,
           t.ten,
           t.ghi_chu
    FROM dm_chung.tinh t
    WHERE
        p_search IS NULL OR p_search = ''
        OR unaccent(lower(t.ten)) LIKE '%' || unaccent(lower(p_search)) || '%'
    ORDER BY t.ten
    OFFSET p_offset
    LIMIT p_limit;
END;
$$ LANGUAGE plpgsql;
