package Bulletin.persistence.Admin;

import Bulletin.persistence.EntityManagerHandler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import jakarta.persistence.NoResultException;

import java.util.List;

public class AdminService {
    private final EntityManager entityManager = EntityManagerHandler.getEntityManager();

    private static AdminService instance = null;

    /**
     *
     * @return AdminService || null
     * @description Cette méthode retourne une seulle instance de l'objet AdminService
     */
    public static AdminService getInstance() {
        return instance == null ? instance = new AdminService() : instance;
    }

    public void createAdmin(Admin admin){
        entityManager.getTransaction().begin();
        entityManager.persist(admin);
        entityManager.getTransaction().commit();
    }

    public Admin getAdmin(String username, String password){
        try {
        Query query = entityManager.createQuery("SELECT a FROM Admin a where ( a.username = :username or a.name = :username ) and a.password = :password", Admin.class);
        query.setParameter("username", username);
        query.setParameter("password",password);
            return (Admin) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }
    public Admin getAdminByUsername(String username){
        try {
            Query query = entityManager.createQuery("SELECT a FROM Admin a where a.username = :username", Admin.class);
            query.setParameter("username", username);
            return (Admin) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
    public void changeMdp(int id,String mdp){
        Admin admin = entityManager.find(Admin.class,id);
        entityManager.getTransaction().begin();
        admin.setPassword(mdp);
        entityManager.getTransaction().commit();
    }
    public Admin getAdminById(int id){
        try {
            return entityManager.find(Admin.class,id);
        }catch (NoResultException e){
            return null;
        }
    }
    public void updateAdmin(int id,Admin admin){
        Admin admin1 = entityManager.find(Admin.class,id);
        entityManager.getTransaction().begin();
        admin1.setUsername(admin.getUsername());
        admin1.setName(admin.getName());
        admin1.setPassword(admin.getPassword());
        admin1.setRule(admin.getRule());
        admin1.setPoste(admin.getPoste());
        entityManager.getTransaction().commit();
    }

    public List<Admin> getAllAdmin(){
        Query query = entityManager.createQuery("SELECT ad FROM Admin ad where ad.rule = :rule", Admin.class);
        query.setParameter("rule",Rules.ADMIN);
        return query.getResultList();
    }
    public List<Admin> getAllUtilisateur(){
        Query query = entityManager.createQuery("SELECT ad FROM Admin ad where ad.rule = :rule", Admin.class);
        query.setParameter("rule",Rules.USER);
        return query.getResultList();
    }

    public void removeAdmin(Admin admin){
        entityManager.getTransaction().begin();
        entityManager.remove(admin);
        entityManager.getTransaction().commit();
    }
}
