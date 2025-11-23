CREATE OR REPLACE FUNCTION school.fn_import_truong(p_truongs truong_input[])
RETURNS void AS $$
BEGIN
    INSERT INTO school.truong(ten, dia_chi, hinh_anh, logo)
    SELECT t.ten, t.dia_chi, t.hinh_anh, t.logo
    FROM unnest(p_truongs) t;
END;
$$ LANGUAGE plpgsql;
