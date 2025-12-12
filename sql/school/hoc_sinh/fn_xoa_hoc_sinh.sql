DROP FUNCTION IF EXISTS school.fn_xoa_hoc_sinh;

CREATE OR REPLACE FUNCTION school.fn_xoa_hoc_sinh(
    p_id BIGINT
)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
DECLARE
    v_exists BOOLEAN;
BEGIN
    SELECT EXISTS(
        SELECT 1 FROM auth.users
        WHERE id = p_id AND role ILIKE '%STUDENT%'
    ) INTO v_exists;

    IF NOT v_exists THEN
        RETURN FALSE;
    END IF;

    -- Xoá
    DELETE FROM auth.users WHERE id = p_id;

    RETURN TRUE;
END;
$$;
