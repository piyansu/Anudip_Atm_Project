package atm.dao;

import atm.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class AccountDAO {

    private EntityManager entityManager;

    public AccountDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankmanagementsystem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveAccount(Account account) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(account);
        transaction.commit();
    }

    public List<Account> getAllAccounts() {
        return entityManager.createNativeQuery("SELECT a FROM Account a", Account.class).getResultList();
    }
}
