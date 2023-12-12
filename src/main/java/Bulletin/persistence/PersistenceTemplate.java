package Bulletin.persistence;

import Bulletin.persistence.condamnation.Condamnation;
import Bulletin.persistence.condamnation.CondamnationService;
import Bulletin.persistence.infoCondamnation.InfoConserned;
import Bulletin.persistence.infoCondamnation.InfoConsernedService;

public class PersistenceTemplate {

    private static PersistenceTemplate instance = null;

    /**
     * @return PersistenceTemplate.class
     * @description Cette méthode retourne l'instance de l'objet PersistenceTemplate
     */
    public static  PersistenceTemplate getInstance() {
        return instance == null ? instance = new PersistenceTemplate() : null;
    }
}
