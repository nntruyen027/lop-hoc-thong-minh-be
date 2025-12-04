CREATE OR REPLACE FUNCTION dm_chung.fn_sua_xa
(
    p_id        BIGINT,
    p_ten       VARCHAR(120),
    p_ghi_chu   TEXT,
    p_tinh_id   BIGINT
)
RETURNS TABLE
(
    out_id      BIGINT,
    ten         VARCHAR(120),
    ghi_chu     TEXT,
    tinh_id     BIGINT,
    ten_tinh    VARCHAR(120)
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
    IF NOT EXISTS (SELECT 1 FROM dm_chung.xa where id = p_id) THEN
        RAISE EXCEPTION 'Xã với id % không tồn tại', p_id;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM dm_chung.tinh where id = p_tinh_id) THEN
        RAISE EXCEPTION 'Tỉnh với id % không tồn tại', p_tinh_id;
    END IF;

    UPDATE dm_chung.xa
    SET
        ten = p_ten,
        ghi_chu = p_ghi_chu,
        tinh_id = p_tinh_id
    WHERE id = p_id;

    RETURN QUERY
    SELECT
        x.id AS out_id,
        x.ten,
        x.ghi_chu,
        x.tinh_id,
        t.ten AS ten_tinh
    FROM dm_chung.xa x
    LEFT JOIN dm_chung.tinh t ON t.id = x.tinh_id
    WHERE x.id = p_id
    LIMIT 1;

END;
$$ LANGUAGE plpgsql;
