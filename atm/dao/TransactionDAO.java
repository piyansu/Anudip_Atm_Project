package atm.dao;

import atm.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TransactionDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public TransactionDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ATM_PU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    public Transaction getTransactionById(int transactionId) {
        return entityManager.find(Transaction.class, transactionId);
    }

    public List<Transaction> getAllTransactions() {
        Query query = entityManager.createQuery("SELECT t FROM Transaction t");
        return query.getResultList();
    }

    public void updateTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.merge(transaction);
        entityManager.getTransaction().commit();
    }

    public void deleteTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(transaction) ? transaction : entityManager.merge(transaction));
        entityManager.getTransaction().commit();
    }
}
