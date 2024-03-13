package atm.dao;

import atm.entity.ATM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ATMDAO {

    private EntityManager entityManager;

    public ATMDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankmanagementsystem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveATM(ATM atm) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(atm);
        transaction.commit();
    }

    public List<ATM> getAllATMs() {
        return entityManager.createNativeQuery("SELECT a FROM ATM a", ATM.class).getResultList();
    }
}
