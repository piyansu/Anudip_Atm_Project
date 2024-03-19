package atm.dao;

import atm.entity.CardInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CardInfoDAO {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CardInfoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ATM_PU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void createCardInfo(CardInfo cardInfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(cardInfo);
        entityManager.getTransaction().commit();
    }

    public CardInfo getCardInfoByAccountId(int accountId) {
        return entityManager.find(CardInfo.class, accountId);
    }

    public List<CardInfo> getAllCardInfos() {
        Query query = entityManager.createQuery("SELECT c FROM CardInfo c");
        return query.getResultList();
    }

    public void updateCardInfo(CardInfo cardInfo) {
        entityManager.getTransaction().begin();
        entityManager.merge(cardInfo);
        entityManager.getTransaction().commit();
    }

    public void deleteCardInfo(CardInfo cardInfo) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(cardInfo) ? cardInfo : entityManager.merge(cardInfo));
        entityManager.getTransaction().commit();
    }
}
