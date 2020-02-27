package br.com.spintec.logicae.geobatch.repository.impl;

import br.com.spintec.logicae.geobatch.repository.custom.LesenseBatchRepositoryCustom;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

public class LesenseBatchRepositoryImpl implements LesenseBatchRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateData(String serial) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("generate_data");
        query.setParameter("serial_p",serial);
        query.execute();
    }
}
