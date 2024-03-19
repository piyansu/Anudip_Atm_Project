package atm.dao;

import atm.entity.ATM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ATMDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ATMDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ATM_PU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createATM(ATM atm) {
        entityManager.getTransaction().begin();
        entityManager.persist(atm);
        entityManager.getTransaction().commit();
    }

    public ATM getATMById(int atmId) {
        return entityManager.find(ATM.class, atmId);
    }

    public List<ATM> getAllATMs() {
        Query query = entityManager.createQuery("SELECT a FROM ATM a");
        return query.getResultList();
    }

    public void updateATM(ATM atm) {
        entityManager.getTransaction().begin();
        entityManager.merge(atm);
        entityManager.getTransaction().commit();
    }

    public void deleteATM(ATM atm) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(atm) ? atm : entityManager.merge(atm));
        entityManager.getTransaction().commit();
    }
}
