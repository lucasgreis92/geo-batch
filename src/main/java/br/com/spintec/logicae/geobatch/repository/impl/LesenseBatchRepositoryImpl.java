package br.com.spintec.logicae.geobatch.repository.impl;

import br.com.spintec.logicae.geobatch.repository.custom.LesenseBatchRepositoryCustom;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class LesenseBatchRepositoryImpl implements LesenseBatchRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateData(String serial) {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("generate_data");
        proc.registerStoredProcedureParameter("serial_p",String.class, ParameterMode.IN);
        proc.setParameter("serial_p",serial);
        proc.execute();
    }
}
