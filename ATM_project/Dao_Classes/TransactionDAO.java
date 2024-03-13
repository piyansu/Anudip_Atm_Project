package atm.dao;

import atm.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionDAO {

    private EntityManager entityManager;

    public TransactionDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankmanagementsystem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveTransaction(Transaction transaction) {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(transaction);
        tx.commit();
    }

    public List<Transaction> getAllTransactions() {
        return entityManager.createNativeQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }
}
