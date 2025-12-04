CREATE OR REPLACE FUNCTION dm_chung.fn_tao_xa
(
    p_ten VARCHAR(120),
    p_ghi_chu TEXT,
    p_tinh_id BIGINT
)
RETURNS TABLE
(
    out_id BIGINT,
    ten VARCHAR(120),
    ghi_chu TEXT,
    tinh_id BIGINT,
    ten_tinh VARCHAR(120)
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
    INSERT INTO dm_chung.xa (
        ten,
        ghi_chu,
        tinh_id
    )
    VALUES (
        p_ten,
        p_ghi_chu,
        p_tinh_id
    )
    RETURNING id INTO new_id;

    RETURN QUERY
    SELECT
        x.id AS out_id,
        x.ten,
        x.ghi_chu,
        x.tinh_id,
        t.ten AS ten_tinh
    FROM dm_chung.xa x
    LEFT JOIN dm_chung.tinh t ON t.id = x.tinh_id
    WHERE x.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
