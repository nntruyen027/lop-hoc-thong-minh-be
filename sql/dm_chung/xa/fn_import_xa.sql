CREATE OR REPLACE FUNCTION dm_chung.fn_import_xa(p_xas dm_chung.xa_input[])
RETURNS void AS $$
BEGIN
    INSERT INTO dm_chung.xa(ten, ghi_chu, tinh_id)
    SELECT t.ten, t.ghi_chu, t.tinh_id
    FROM unnest(p_xas) t;
END;
$$ LANGUAGE plpgsql;
