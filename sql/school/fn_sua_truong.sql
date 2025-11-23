CREATE OR REPLACE FUNCTION school.fn_sua_giao_vien
(
    p_id BIGINT,
    p_ten VARCHAR(120),
    p_dia_chi TEXT,
    p_hinh_anh TEXT,
    p_logo TEXT
)
RETURNS TABLE
(
    out_id BIGINT,
    ten VARCHAR(120),
    dia_chi TEXT,
    hinh_anh TEXT,
    logo VARCHAR(50)
)
AS $$
BEGIN
    UPDATE school.truong
    SET
        ten = p_ten,
        dia_chi = p_dia_chi,
        hinh_anh = p_hinh_anh,
        logo = p_logo
    WHERE id = p_id;

    RETURN QUERY
        SELECT
            t.id AS out_id,
            t.ho_ten,
            t.dia_chi,
            t.hinh_anh,
            t.logo
        FROM school.truong t
        WHERE u.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
