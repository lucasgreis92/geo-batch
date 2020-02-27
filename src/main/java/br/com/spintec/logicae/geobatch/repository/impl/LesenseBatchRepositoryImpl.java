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
        Query query =em.createNativeQuery("select generate_data('"+serial+"')");
        query.getSingleResult();
    }
}
