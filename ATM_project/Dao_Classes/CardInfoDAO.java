package atm.dao;

import atm.entity.CardInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CardInfoDAO {

    private EntityManager entityManager;

    public CardInfoDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankmanagementsystem");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveCardInfo(CardInfo cardInfo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cardInfo);
        transaction.commit();
    }

    public List<CardInfo> getAllCardInfo() {
        return entityManager.createNativeQuery("SELECT c FROM CardInfo c", CardInfo.class).getResultList();
    }
}

