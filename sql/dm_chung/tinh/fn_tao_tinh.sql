CREATE OR REPLACE FUNCTION dm_chung.fn_tao_tinh
(
    p_ten VARCHAR(120),
    p_ghi_chu TEXT
)
RETURNS TABLE
(
    out_id BIGINT,
    ten VARCHAR(120),
    ghi_chu TEXT
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
    INSERT INTO dm_chung.tinh (
        ten,
        ghi_chu
    )
    VALUES (
        p_ten,
        p_ghi_chu
    )
    RETURNING id INTO new_id;

    RETURN QUERY
    SELECT
        t.id AS out_id,
        t.ten,
        t.ghi_chu
    FROM dm_chung.tinh t
    WHERE t.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
