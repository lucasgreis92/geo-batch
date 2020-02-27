package br.com.spintec.logicae.geobatch.repository.impl;

import br.com.spintec.logicae.geobatch.repository.custom.DevicesRepositoryCustom;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DevicesRepositoryImpl implements DevicesRepositoryCustom {

/*    @PersistenceContext
    private EntityManager em;*/

/*    @Override
    public void generateData(String serial) {

        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                String query = "{call generate_data(?)}";
                CallableStatement statement = connection.prepareCall(query);
                statement.setString(1, "serial");
                statement.execute();
            }
        });
        session.flush();
  *//*      em.flush();
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("generate_data");
        query.setParameter("serial_p",serial);
        query.execute();
        em.flush();*//*
    }*/
}
