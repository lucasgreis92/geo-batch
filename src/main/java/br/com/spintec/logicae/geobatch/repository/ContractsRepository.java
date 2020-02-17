package br.com.spintec.logicae.geobatch.repository;

import br.com.spintec.logicae.geobatch.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractsRepository  extends JpaRepository<Contracts, UUID> {

}
