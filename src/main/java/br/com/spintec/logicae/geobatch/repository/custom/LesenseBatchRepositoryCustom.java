package br.com.spintec.logicae.geobatch.repository.custom;

import org.springframework.transaction.annotation.Transactional;

public interface LesenseBatchRepositoryCustom {
    @Transactional
    void generateData(String serial);
}
