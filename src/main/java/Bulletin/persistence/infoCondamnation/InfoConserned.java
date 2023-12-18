package Bulletin.persistence.infoCondamnation;

import Bulletin.persistence.condamnation.Condamnation;
import jakarta.persistence.*;

import java.util.*;

@Entity @Table(name = "infoConserned")
public class InfoConserned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConserned", nullable = false)
    private int idConserned;

    @OneToMany(mappedBy = "infoConserned", orphanRemoval = true)
    private List<Condamnation> condamnations = new ArrayList<>();

    public InfoConserned() {
        this(0, "uknown", "uknown", "uknown", "uknown",
                null, "uknown", "uknown", "uknown",
                "uknown", "uknown", "uknown");
    }

    public InfoConserned(int acteNaissance, String nom, String prenoms, String pere, String mere, Date dateNaissance, String lieuNaissance, String situationFamiliale, String profession, String domicile, String sexe, String nationalite) {
        this.acteNaissance = acteNaissance;
        this.nom = nom;
        this.prenoms = prenoms;
        this.pere = pere;
        this.mere = mere;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.situationFamiliale = situationFamiliale;
        this.profession = profession;
        this.domicile = domicile;
        this.sexe = sexe;
        this.nationalite = nationalite;
    }
    public InfoConserned(int acteNaissance, String nom, String prenoms, String pere, String mere, Date dateNaissance, String lieuNaissance, String situationFamiliale, String profession, String domicile, String sexe) {
        this.acteNaissance = acteNaissance;
        this.nom = nom;
        this.prenoms = prenoms;
        this.pere = pere;
        this.mere = mere;
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
    @OneToMany(targetEntity = Condamnation.class,mappedBy = "infoConserned")
    private List<Condamnation> condamnationList = new ArrayList<Condamnation>();

    public List<Condamnation> getCondamnations() {
        return condamnations;
    }

    public void setCondamnations(List<Condamnation> condamnations) {
        this.condamnations = condamnations;
    }

    public List<Condamnation> getCondamnationList() {
        return condamnationList;
    }
    public void addCondamnation(Condamnation condamnation){
        this.condamnationList.add(condamnation);
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getPere() {
        return pere;
    }

    public void setPere(String pere) {
        this.pere = pere;
    }

    public String getMere() {
        return mere;
    }

    public void setMere(String mere) {
        this.mere = mere;
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
                ", condamnationList=" + condamnationList +
                '}';
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
}
