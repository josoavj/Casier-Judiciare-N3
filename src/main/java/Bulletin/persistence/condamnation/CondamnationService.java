package Bulletin.persistence.condamnation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class CondamnationService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bulletin");
    private final EntityManager entityManager = emf.createEntityManager();
    private static CondamnationService instance = null;

    /**
     * @return CondamantionService.class
     * @description Cette méthode retourne l'instance de l'objet CondamantionService
     */
    public static  CondamnationService getInstance() {
        return instance == null ? instance = new CondamnationService() : null;
    }

    /**
     * @return List<Condamnation>
     * @description Cette fonction retourne la liste des éléments dans la table 'condamantion'
     */

    public List<Condamnation> getCondamnationList(){
        Query query = entityManager.createQuery("SELECT c FROM Condamnation c", Condamnation.class);
        return query.getResultList();
    }

    /**
     * @param condamnation
     * @paramType Condamnation.class
     * @description Cette fonction ajoute un element dans la table 'condamnation'
     */
    public void createCondamnation(Condamnation condamnation){

        entityManager.getTransaction().begin();
        entityManager.persist(condamnation);
        entityManager.getTransaction().commit();
    }

    /**
     * @param condamnation
     * @paramType Condamnation.class
     * @description cette methode efface une condamantion dans la table 'condamnation'
     */
    public void removeCondamnation(Condamnation condamnation){
        entityManager.getTransaction().begin();
        entityManager.remove(condamnation);
        entityManager.getTransaction().commit();
    }

    /**
     * @param  id
     * @paramType int
     * @return Objet de type Condamnation
     * @description recupere un element de la table 'condamnation' par son id
     */
    public Condamnation getCondamnationById(int id){
        return entityManager.find(Condamnation.class,id);
    }
}
