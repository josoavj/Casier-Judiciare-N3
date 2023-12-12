package Bulletin.persistence.Admin;

import Bulletin.persistence.condamnation.CondamnationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jdk.jfr.Percentage;

public class AdminService {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Bulletin");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private AdminService instance = null;

    /**
     *
     * @return AdminService || null
     * @description Cette méthode retourne une seulle instance de l'objet AdminService
     */
    public AdminService getInstance() {
        return instance == null ? instance = new AdminService() : null;
    }

    public void createAdmin(Admin admin){
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }

    public Admin getAdmin(String username, String password){
        Query query = entityManager.createQuery("SELECT a FROM Admin a where a.usename = :username and a.password = :password", Admin.class);
        query.setParameter("username", username);
        query.setParameter("password",password);
        return (Admin) query.getSingleResult();
    }

    public void removeAdmin(Admin admin){
        entityManager.getTransaction().begin();
        entityManager.remove(admin);
        entityManager.getTransaction().commit();
    }
}
