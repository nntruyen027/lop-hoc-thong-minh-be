package com.vinhthanh2.lophocdientu.repository;


import com.vinhthanh2.lophocdientu.entity.CauHoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CauHoiRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<CauHoi> layCauHoiTheoNhom(Long nhomId, String search, int page, int size) {
        int offset = (page - 1) * size;

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("layCauHoiTheoNhom", CauHoi.class);

        query.registerStoredProcedureParameter("p_search", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nhom_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_offset", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);

        query.setParameter("p_search", search);
        query.setParameter("p_nhom_id", nhomId);
        query.setParameter("p_offset", offset);
        query.setParameter("p_limit", size);

        return query.getResultList();
    }
}
