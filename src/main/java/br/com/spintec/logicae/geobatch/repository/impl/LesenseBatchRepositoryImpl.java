package br.com.spintec.logicae.geobatch.repository.impl;

import br.com.spintec.logicae.geobatch.repository.custom.LesenseBatchRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class LesenseBatchRepositoryImpl implements LesenseBatchRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Override
    public void generateData(String serial) {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("generate_data");
        proc.setParameter("serial_p",serial);
        proc.executeUpdate();
    }
}
