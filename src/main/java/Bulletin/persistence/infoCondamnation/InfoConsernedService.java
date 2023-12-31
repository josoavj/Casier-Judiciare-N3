package Bulletin.persistence.infoCondamnation;

import Bulletin.persistence.EntityManagerHandler;
import Bulletin.persistence.condamnation.Condamnation;
import Bulletin.persistence.condamnation.CondamnationService;
import jakarta.persistence.*;

import jakarta.persistence.NoResultException;
import javax.swing.*;
import java.util.HashSet;
import java.util.List;

public class InfoConsernedService {
    private final EntityManager entityManager = EntityManagerHandler.getEntityManager();
    private static InfoConsernedService instance = null;

    /**
     * @return InfoConsernedService
     * @description Cette fonction retourne l'instence de la class InfoConsernedService
     */
    public static  InfoConsernedService getInstance() {
        return instance == null ? instance = new InfoConsernedService() : instance;
    }

    /**
     * @return List<InfoConserned>
     * @description Cette methode retourne la liste des informations sur les concernés
     * @TableDeSelection 'infoConserned'
     */
    public List<InfoConserned> getConsernedList(){
        try {
            Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic", InfoConserned.class);
            return query.getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    /**
     *
     * @param conserned
     * @paramType InfoConserned.class
     * @description Cette fonction persiste une nouvelle inforamtion dans la table 'infoConserned'
     */
    public boolean addConserned(InfoConserned conserned){
        try {
        EntityTransaction trans = entityManager.getTransaction();
        trans.begin();

        InfoConserned infoConserned = new InfoConserned();
        infoConserned.setNom(conserned.getNom());
        infoConserned.setPrenoms(conserned.getPrenoms());
        infoConserned.setActeNaissance(conserned.getActeNaissance());
        infoConserned.setDateActeNaissance(conserned.getDateActeNaissance());
        infoConserned.setSexe(conserned.getSexe());
        infoConserned.setPere(conserned.getPere());
        infoConserned.setMere(conserned.getMere());
        infoConserned.setSituationFamiliale(conserned.getSituationFamiliale());
        infoConserned.setProfession(conserned.getProfession());
        infoConserned.setNationalite(conserned.getNationalite());
        infoConserned.setDomicile(conserned.getDomicile());
        infoConserned.setDateNaissance(conserned.getDateNaissance());
        infoConserned.setLieuNaissance(conserned.getLieuNaissance());
        entityManager.persist(infoConserned);
        trans.commit();
        InfoConserned cForCondamnation = getInfoConsernedByAN(conserned.getActeNaissance());
        for (Object c : conserned.getCondamnationList()){
            Condamnation condamnation  = (Condamnation) c;
            condamnation.setInfoConserned(cForCondamnation);
            CondamnationService.getInstance().createCondamnation(condamnation);
        }
        return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null , e);
            return false;
        }
    }

    public boolean updateInfoConserned(int id, InfoConserned infoConsernedUpdated){
        try {
            InfoConserned infoConserned = getConsernedById(id);
            EntityTransaction trans  = entityManager.getTransaction();
            trans.begin();
            infoConserned.setNom(infoConsernedUpdated.getNom());
            infoConserned.setPrenoms(infoConsernedUpdated.getPrenoms());
            infoConserned.setActeNaissance(infoConsernedUpdated.getActeNaissance());
            infoConserned.setDateActeNaissance(infoConsernedUpdated.getDateActeNaissance());
            infoConserned.setSexe(infoConsernedUpdated.getSexe());
            infoConserned.setPere(infoConsernedUpdated.getPere());
            infoConserned.setMere(infoConsernedUpdated.getMere());
            infoConserned.setSituationFamiliale(infoConsernedUpdated.getSituationFamiliale());
            infoConserned.setProfession(infoConsernedUpdated.getProfession());
            infoConserned.setNationalite(infoConsernedUpdated.getNationalite());
            infoConserned.setDomicile(infoConsernedUpdated.getDomicile());
            infoConserned.setDateNaissance(infoConsernedUpdated.getDateNaissance());
            infoConserned.setLieuNaissance(infoConsernedUpdated.getLieuNaissance());
            List<Condamnation> condamnationList = infoConserned.getCondamnations();
            for (int i = 0; i < condamnationList.size(); i++) {
                boolean remove = true;
                for (Condamnation c : infoConsernedUpdated.getCondamnations()) {
                    if (infoConserned.getCondamnations().get(i).getIdCondamnation() == c.getIdCondamnation()) {
                        infoConserned.getCondamnations().get(i).setDateCondamnation(c.getDateCondamnation());
                        infoConserned.getCondamnations().get(i).setNaturePeine(c.getNaturePeine());
                        infoConserned.getCondamnations().get(i).setNatureCrime(c.getNatureCrime());
                        infoConserned.getCondamnations().get(i).setObservation(c.getObservation());
                        infoConserned.getCondamnations().get(i).setInfoConserned(infoConserned);
                        remove = false;
                    }
                }
                if (remove) {
                    infoConserned.getCondamnations().get(i).setInfoConserned(null);
                    condamnationList.remove(infoConserned.getCondamnations().get(i));
                }
            }
            for (Condamnation c : infoConsernedUpdated.getCondamnations()) {
                if (c.getIdCondamnation() == 0) {
                    c.setInfoConserned(infoConserned);
                    condamnationList.add(c);
                    CondamnationService.getInstance().createCondamnation(c);
                }
            }
            trans.commit();
            CondamnationService.getInstance().cleanTable();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
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
        InfoConserned infoConserned = entityManager.find(InfoConserned.class,id);
        entityManager.refresh(infoConserned);
        return infoConserned;
    }

    /**
     *
     * @param searchQuery
     * @paramType String
     * @return List<InfoConserned> || InfoConserned
     * @description Cette fait une recherche d'information dans la ase de donnée par le nom ou prenoms de du consernée
     * @TableDeSelection 'infoConserned'
     */

    /**
     *
     * @param infoConserned
     * @paramType InfoConserned.class
     * @description Cette fonction efface l'element qui est passée en paramètre de la table 'infoConserned'
     */
    public void removeInfoConserned(InfoConserned infoConserned){
        entityManager.refresh(infoConserned);
        entityManager.getTransaction().begin();
        entityManager.remove(infoConserned);
        entityManager.getTransaction().commit();
    }

    public InfoConserned getInfoConsernedByAN(int acteNaissace){
        try {
            Query query = entityManager.createQuery("SELECT ic FROM InfoConserned ic WHERE ic.acteNaissance = :acteNaissance",
                    InfoConserned.class);
            query.setParameter("acteNaissance",acteNaissace);
            InfoConserned infoConserned =  (InfoConserned) query.getSingleResult();
            entityManager.refresh(infoConserned);
            return infoConserned;
        }catch (NoResultException e){
            return null;
        }

    }

}
