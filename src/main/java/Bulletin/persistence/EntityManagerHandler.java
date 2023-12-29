package Bulletin.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerHandler {
    private static final EntityManagerFactory emf =  Persistence.createEntityManagerFactory("Bulletin");

    private  static EntityManager entityManager = null;
    public static EntityManager getEntityManager(){
        return entityManager == null ? emf.createEntityManager() : entityManager;
    }
}
