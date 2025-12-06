DROP FUNCTION IF EXISTS school.fn_tao_truong;

CREATE OR REPLACE FUNCTION school.fn_tao_truong
(
    p_ten VARCHAR(120),
	p_xa_id BIGINT,
    p_dia_chi_chi_tiet VARCHAR(500),
    p_hinh_anh TEXT,
    p_logo TEXT
)
RETURNS TABLE
(
    out_id BIGINT,
    ten VARCHAR(120),
	xa_id BIGINT,
	ten_xa VARCHAR(120),
	tinh_id BIGINT,
	ten_tinh VARCHAR(120),
    dia_chi_chi_tiet VARCHAR(500),
    hinh_anh TEXT,
    logo TEXT
)
AS $$
DECLARE
    new_id BIGINT;
BEGIN
	IF NOT EXISTS (select 1 from dm_chung.xa x where x.id = p_xa_id) THEN
    		raise exception 'Xã với id % không tồn tại', p_xa_id;
    	END IF;

    INSERT INTO school.truong (
        ten,
        dia_chi_chi_tiet,
		xa_id,
        hinh_anh,
        logo
    )
    VALUES (
        p_ten,
        p_dia_chi_chi_tiet,
		p_xa_id,
        p_hinh_anh,
        p_logo
    )
    RETURNING id INTO new_id;

    RETURN QUERY
    SELECT
        t.id AS out_id,
           t.ten,
		   t.xa_id,
		   x.ten AS ten_xa,
		   ti.id AS tinh_id,
		   ti.ten AS ten_tinh,
           t.dia_chi_chi_tiet,
           t.hinh_anh,
           t.logo
    FROM school.truong t
	LEFT JOIN dm_chung.xa x ON x.id = t.xa_id
	LEFT JOIN dm_chung.tinh ti ON ti.id = x.tinh_id
    WHERE t.id = new_id
    LIMIT 1;
END;
$$ LANGUAGE plpgsql;
