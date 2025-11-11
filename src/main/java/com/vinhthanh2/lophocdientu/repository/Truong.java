package com.vinhthanh2.lophocdientu.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Truong {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> layTruong(int page, int size) {
        int offset = (page - 1) * size;

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("layDsTruong");

        query.registerStoredProcedureParameter("p_offset", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_limit", Integer.class, ParameterMode.IN);

        query.setParameter("p_offset", offset);
        query.setParameter("p_limit", size);

        return query.getResultList();
    }
}
