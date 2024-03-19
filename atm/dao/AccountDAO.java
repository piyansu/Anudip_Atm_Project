package atm.dao;

import atm.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AccountDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AccountDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ATM_PU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createAccount(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }

    public Account getAccountById(int accountId) {
        return entityManager.find(Account.class, accountId);
    }

    public List<Account> getAllAccounts() {
        Query query = entityManager.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    public void updateAccount(Account account) {
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
    }

    public void deleteAccount(Account account) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(account) ? account : entityManager.merge(account));
        entityManager.getTransaction().commit();
    }
}
