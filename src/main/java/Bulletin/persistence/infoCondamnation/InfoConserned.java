package Bulletin.persistence.infoCondamnation;

import Bulletin.persistence.condamnation.Condamnation;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity @Table(name = "infoConserned")
public class InfoConserned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConserned", nullable = false)
    private int idConserned;

    @OneToMany(mappedBy = "infoConserned",orphanRemoval = true)
    private List<Condamnation> condamnations = new ArrayList<>();

    public InfoConserned() {
        this(0, java.sql.Date.valueOf(LocalDate.now()), "uknown", "uknown", "uknown", "uknown",
                java.sql.Date.valueOf(LocalDate.now()), "uknown", "uknown", "uknown",
                "uknown", "uknown", "uknown");
    }

    public InfoConserned(int acteNaissance, Date dateActeNaissance, String nom, String prenoms, String pere, String mere, Date dateNaissance, String lieuNaissance, String situationFamiliale, String profession, String domicile, String sexe, String nationalite) {
        this.acteNaissance = acteNaissance;
        this.dateActeNaissance = dateActeNaissance;
        this.nom = nom.toUpperCase();
        String[] prenomSplited = prenoms.split(" ");
        String prenom = null;
        for (String pn : prenomSplited){
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            prenom = prenom +" "+firstLetter+pn.substring(1) ;
        }
        this.prenoms = prenom;
        //pere
        String[] pereSplited = pere.split(" ");
        String newpere = pereSplited[0].toUpperCase();
        for (int i = 1; i < pereSplited.length; i++) {
            String pn = pereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newpere  = newpere + " "+firstLetter + pn.substring(1) ;
        }
        this.pere =  newpere;
        //mere
        String[] mereSplited = mere.split(" ");
        String newmere = mereSplited[0].toUpperCase();
        for (int i = 1; i < mereSplited.length; i++) {
            String pn = mereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newmere  = newmere + " "+firstLetter + pn.substring(1) ;
        }
        this.mere = newmere;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.situationFamiliale = situationFamiliale;
        this.profession = profession;
        this.domicile = domicile;
        this.sexe = sexe;
        this.nationalite = nationalite;
    }
    public InfoConserned(int acteNaissance, Date dateActeNaissance, String nom, String prenoms, String pere, String mere, Date dateNaissance, String lieuNaissance, String situationFamiliale, String profession, String domicile, String sexe) {
        this.acteNaissance = acteNaissance;
        this.dateActeNaissance = dateActeNaissance;
        this.nom = nom.toUpperCase();
        String prenom = null;
        String[] prenomSplited = prenoms.split(" ");
        for (String pn : prenomSplited){
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            prenom = prenom +" "+firstLetter+pn.substring(1) ;
        }
        this.prenoms = prenom;
        //pere
        String[] pereSplited = pere.split(" ");
        String newpere = pereSplited[0].toUpperCase();
        for (int i = 1; i < pereSplited.length; i++) {
            String pn = pereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newpere  = newpere + " "+firstLetter + pn.substring(1) ;
        }
        this.pere =  newpere;
        //mere
        String[] mereSplited = mere.split(" ");
        String newmere = mereSplited[0].toUpperCase();
        for (int i = 1; i < mereSplited.length; i++) {
            String pn = mereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newmere  = newmere + " "+firstLetter + pn.substring(1) ;
        }
        this.mere = newmere;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.situationFamiliale = situationFamiliale;
        this.profession = profession;
        this.domicile = domicile;
        this.sexe = sexe;
        this.nationalite = "MALAGASY";
    }

    public int getIdConserned() {
        return idConserned;
    }
    private int acteNaissance;
    private Date dateActeNaissance;
    private String nom;
    private String prenoms;
    private String pere;
    private String mere;
    private Date dateNaissance;
    private String lieuNaissance;
    private String situationFamiliale;
    private String profession;
    private String domicile;
    private String sexe;
    private String nationalite;

    public List<Condamnation> getCondamnations() {
        return condamnations;
    }

    public void setCondamnations(List<Condamnation> condamnations) {
        this.condamnations = condamnations;
    }
    public void addCondamnation(Condamnation condamnation){
        condamnation.setInfoConserned(this);
        this.condamnations.add(condamnation);
    }
    public void removeCondamnation(Condamnation condamnation){
        this.condamnations.remove(condamnation);
    }
    public void clearCondamnation(){
        this.condamnations.clear();
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        String[] prenomSplited = prenoms.split(" ");
        String prenom = "";
        for (String pn : prenomSplited){
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            prenom = prenom +" "+firstLetter+pn.substring(1) ;
        }
        this.prenoms = prenom;
    }

    public String getPere() {
        return pere;
    }

    public void setPere(String pere) {
        String[] pereSplited = pere.split(" ");
        String newpere = pereSplited[0].toUpperCase();
        for (int i = 1; i < pereSplited.length; i++) {
            String pn = pereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newpere  = newpere +" "+firstLetter+pn.substring(1) ;
        }
        this.pere = newpere;
    }

    public String getMere() {
        return mere;
    }

    public void setMere(String mere) {
        String[] mereSplited = mere.split(" ");
        String newmere = mereSplited[0].toUpperCase();
        for (int i = 1; i < mereSplited.length; i++) {
            String pn = mereSplited[i];
            String firstLetter = String.valueOf(pn.charAt(0));
            firstLetter = firstLetter.toUpperCase();
            newmere  = newmere + " "+firstLetter + pn.substring(1) ;
        }
        this.mere = newmere;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    @Override
    public String toString() {

        return "InfoConserned{" +
                "idConserned=" + idConserned +
                ", nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                ", pere='" + pere + '\'' +
                ", mere='" + mere + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", situationFamiliale='" + situationFamiliale + '\'' +
                ", profession='" + profession + '\'' +
                ", domicile='" + domicile + '\'' +
                ", nationalite='" + nationalite + '\'' +
                '}';
    }
    public List getCondamnationList(){
        List list = new ArrayList();
        for(Condamnation c : condamnations){
            list.add(c);
        }
        return list;
    }
    public int getActeNaissance() {
        return acteNaissance;
    }

    public void setActeNaissance(int acteNaissance) {
        this.acteNaissance = acteNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateActeNaissance() {
        return dateActeNaissance;
    }

    public void setDateActeNaissance(Date dateActeNaissance) {
        this.dateActeNaissance = dateActeNaissance;
    }
}
