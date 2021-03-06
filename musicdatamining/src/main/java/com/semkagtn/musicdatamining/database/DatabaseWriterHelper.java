package com.semkagtn.musicdatamining.database;


import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;

/**
 * Created by semkagtn on 11.03.15.
 */
public class DatabaseWriterHelper {

    private SessionFactory sessionFactory;
    private StatelessSession session;

    public DatabaseWriterHelper() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openStatelessSession();
    }

    public void close() {
        session.close();
        sessionFactory.close();
    }

    public boolean insert(Object object) {
        session.beginTransaction();
        try {
            session.insert(object);
            session.getTransaction().commit();
        } catch (ConstraintViolationException | NonUniqueObjectException | GenericJDBCException e) {
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }
}
