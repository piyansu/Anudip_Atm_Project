package atm.dao;

import atm.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerDAO {

    private EntityManager entityManager;

    public CustomerDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankmanagementsystem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveCustomer(Customer customer) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
    }

    public List<Customer> getAllCustomers() {
        return entityManager.createNativeQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }
}
