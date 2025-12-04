CREATE OR REPLACE FUNCTION dm_chung.fn_sua_tinh
(
    p_id BIGINT,
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
BEGIN
    IF NOT EXISTS (SELECT 1 FROM dm_chung.tinh t WHERE t.id = p_id) THEN
        RAISE EXCEPTION USING MESSAGE = format('Tỉnh với id %s không tồn tại', p_id);
    END IF;

    UPDATE dm_chung.tinh
    SET
        ten = p_ten,
        ghi_chu = p_ghi_chu
    WHERE id = p_id;

    RETURN QUERY
        SELECT t.id, t.ten, t.ghi_chu
        FROM dm_chung.tinh t
        WHERE t.id = p_id;
END;
$$ LANGUAGE plpgsql;
