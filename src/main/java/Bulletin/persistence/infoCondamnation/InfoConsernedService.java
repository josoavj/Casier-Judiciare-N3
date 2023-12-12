package Bulletin.persistence.infoCondamnation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.HashSet;
import java.util.List;

public class InfoConsernedService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bulletin");
    private final EntityManager entityManager = emf.createEntityManager();
    private static InfoConsernedService instance = null;

    /**
     * @return InfoConsernedService
     * @description Cette fonction retourne l'instence de la class InfoConsernedService
     */
    public static  InfoConsernedService getInstance() {
        return instance == null ? instance = new InfoConsernedService() : null;
    }

    /**
     * @return List<InfoConserned>
     * @description Cette methode retourne la liste des informations sur les concernés
     * @TableDeSelection 'infoConserned'
     */
    public List<InfoConserned> getConsernedList(){
        Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic", InfoConserned.class);
        return query.getResultList();
    }

    /**
     *
     * @param conserned
     * @paramType InfoConserned.class
     * @description Cette fonction persiste une nouvelle inforamtion dans la table 'infoConserned'
     */
    public void addConserned(InfoConserned conserned){
        entityManager.getTransaction().begin();
        entityManager.persist(conserned);
        entityManager.getTransaction().commit();
    }

    /**
     *
     * @param id
     * @paramType int
     * @return InfoConserned
     * @description Cette fonction selectionne une information sur le/la conserné(e) par son id
     * @TableDeSelection 'infoConserned'
     */
    public InfoConserned getConsernedById(int id){
        return entityManager.find(InfoConserned.class,id);
    }

    /**
     *
     * @param searchQuery
     * @paramType String
     * @return List<InfoConserned> || InfoConserned
     * @description Cette fait une recherche d'information dans la ase de donnée par le nom ou prenoms de du consernée
     * @TableDeSelection 'infoConserned'
     */
    public HashSet<InfoConserned> getConsernedByName(String searchQuery) {
        HashSet<InfoConserned> queryResults = new HashSet<InfoConserned>();
        HashSet<InfoConserned> finalResult = new HashSet<InfoConserned>();
        boolean specFound = false;

        Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic WHERE ic.nom LIKE :search " +
                "OR ic.prenoms LIKE :search", InfoConserned.class);
        for (String search : searchQuery.split(" ")
        ) {
            query.setParameter("search", "%" + search + "%");
            queryResults.addAll(query.getResultList());
        }
        for (InfoConserned result:queryResults) {
            if ((result.getNom() + " " + result.getPrenoms()).contains(searchQuery) ||
                    (result.getPrenoms() + " " + result.getNom()).contains(searchQuery)){
               specFound = true;
               finalResult.add(result);
            }
        }
        if(specFound){return finalResult;}
        finalResult = queryResults;
        return finalResult;
    }

    /**
     *
     * @param infoConserned
     * @paramType InfoConserned.class
     * @description Cette fonction efface l'element qui est passée en paramètre de la table 'infoConserned'
     */
    public void removeInfoConserned(InfoConserned infoConserned){
        entityManager.getTransaction().begin();
        entityManager.remove(infoConserned);
        entityManager.getTransaction().commit();
    }

}
