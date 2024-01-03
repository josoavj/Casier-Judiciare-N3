package Bulletin.UI;


import Bulletin.persistence.condamnation.Condamnation;
import Bulletin.persistence.condamnation.CondamnationService;
import Bulletin.persistence.infoCondamnation.InfoConserned;
import Bulletin.persistence.infoCondamnation.InfoConsernedService;
import Bulletin.print.PrinterService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class AjoutPersonne extends javax.swing.JFrame {
InfoConsernedService infoConsernedService = null;
CondamnationService condamnationService = null;
    /**
     * Creates new form PatientRegistration
     *
     */
    public static List<Condamnation> listeDeCondamnations = new ArrayList<>();
    public static List<Condamnation> listCondamnationWillRemoved = new ArrayList<>();
    public static List<Condamnation> listCondamnationAdded = new ArrayList<>();
    private InfoConserned infoConserned;

    public InfoConserned getInfoConserned() {
        return infoConserned;
    }

    public void setInfoConserned(InfoConserned infoConserned) {

        this.infoConserned = infoConserned;
    }

    public AjoutPersonne() {
        initComponents();
        setLocationRelativeTo(null);
        lister_Condamnation();
    }
    public AjoutPersonne(InfoConserned infoConserned){
        initComponents();
        setLocationRelativeTo(null);
        this.setInfoConserned(infoConserned);
        acteNaissace.setText(String.valueOf(infoConserned.getActeNaissance()));
        jourAct.setText(infoConserned.getDateActeNaissance().toString().split("-")[2].split(" ")[0]);
        anneAct.setText(infoConserned.getDateActeNaissance().toString().split("-")[0]);
        moisAct.setSelectedIndex(Integer.parseInt(infoConserned.getDateActeNaissance().toString().split("-")[1])-1);
        NomPers.setText(infoConserned.getNom());
        PrenomPers.setText(infoConserned.getPrenoms());
        mere.setText(infoConserned.getMere());
        pere.setText(infoConserned.getPere());
        lieunais.setText(infoConserned.getLieuNaissance());
        datenaiss.setText(String.valueOf(infoConserned.getDateNaissance()));
        Profession.setText(infoConserned.getProfession());
        Domicile.setText(infoConserned.getDomicile());
        Nationalite.setText(infoConserned.getNationalite());
        Pattern pattern = Pattern.compile("[ /-]");
        String[] dateNaiss = pattern.split(infoConserned.getDateNaissance().toString());
        datenaiss.setText(dateNaiss[2]);
        moisnaiss.setSelectedIndex(Integer.parseInt(dateNaiss[1])-1);
        annenaiss.setText(dateNaiss[0]);
        int genderIndex = infoConserned.getSexe().equals("Masculin") ? 0 : 1;
        cmbGender.setSelectedIndex(genderIndex);
        switch (infoConserned.getSituationFamiliale()){
            case "Célibataire" : cmbStatus.setSelectedIndex(0);
            break;
            case "Marié" : case "Mariée" :cmbStatus.setSelectedIndex(1);
            break;
            case "Veuf" : case "Veuve" : cmbStatus.setSelectedIndex(2);
            break;
        }
        for(Object c : infoConserned.getCondamnationList()){
            listeDeCondamnations.add((Condamnation) c);
        }
        lister_Condamnation();
        AjoutForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations Consernant " + infoConserned.getNom()));
        this.setTitle("Modifications des informations Concernant " + infoConserned.getNom());
        btnEffacer.setEnabled(true);
        btnMaj.setEnabled(true);
        btnEnregistrer.setEnabled(false);
    }

    /**
     * @description cette methode liste les condamnation lié au concerné
     */
    public static void lister_Condamnation(){
        tableCondamnation.setEnabled(true);
        Object[][] datas = new Object[listeDeCondamnations.size()][4];
        int i=0;
        for (Condamnation condamnation : listeDeCondamnations){
            datas[i][0] = condamnation.getDateCondamnation();
            datas[i][1] = condamnation.getCoursOutrubinaux();
            datas[i][2] = condamnation.getNatureCrime();
            datas[i][3] = condamnation.getNaturePeine();
            i++;
        }
        String[] colomnNames = new String[4];
        colomnNames[0] = "Date de Condamnation";
        colomnNames[1] = "Cours ou Trubinaux";
        colomnNames[2] = "Nature des crimes ou délits";
        colomnNames[3] = "Nature et durée de peine";
        DefaultTableModel defaultTableModel = new DefaultTableModel(datas,colomnNames);
        tableCondamnation.setModel(defaultTableModel);
    }
private void Reset()
{
    ListePersonne.getInstance().dispose();
    acteNaissace.setText("");
    NomPers.setText("");
    PrenomPers.setText("");
    mere.setText("");
    pere.setText("");
    lieunais.setText("");
    datenaiss.setText("");
    Profession.setText("");
    Domicile.setText("");
    Nationalite.setText("");
    annenaiss.setText("");
    anneAct.setText("");
    jourAct.setText("");
    moisAct.setSelectedIndex(0);
    cmbStatus.setSelectedIndex(0);
    cmbGender.setSelectedIndex(0);
    listeDeCondamnations.clear();
    listCondamnationAdded.clear();
    listCondamnationWillRemoved.clear();
    btnEnregistrer.setEnabled(true);
    btnMaj.setEnabled(false);
    btnEffacer.setEnabled(false);
    acteNaissace.requestFocus(true);
    AjoutForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Ajout d'une nouvelle personne "));
    this.setTitle("Ajout d'une nouvelle personne ");
    infoConserned = null;
    lister_Condamnation();
}
    private String Capitalize(String str){
        return str.toUpperCase().charAt(0)+str.substring(1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AjoutForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        acteNaissace = new javax.swing.JTextField();
        NomPers = new javax.swing.JTextField();
        PrenomPers = new javax.swing.JTextField();
        pere = new javax.swing.JTextField();
        lieunais = new javax.swing.JTextField();
        datenaiss = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        mere = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Profession = new javax.swing.JTextField();
        Sexe = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Domicile = new javax.swing.JTextField();
        Nationalite = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnAjoutCondamnation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCondamnation = new javax.swing.JTable();
        moisnaiss = new javax.swing.JComboBox<>();
        annenaiss = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jourAct = new javax.swing.JTextField();
        moisAct = new javax.swing.JComboBox<>();
        anneAct = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnNouveau = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnEffacer = new javax.swing.JButton();
        btnMaj = new javax.swing.JButton();
        btnGetInfo = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajout nouvelle d'une personne");
        setResizable(true);

        AjoutForm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ajout d'une nouvelle personne", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 1, 11))); // NOI18N

        jLabel1.setText("Acte De naissance N°");

        jLabel2.setText("Nom ");

        jLabel3.setText("Profession");

        jLabel4.setText("Père");

        jLabel5.setText("Mère");

        jLabel6.setText("Date de naissance");

        acteNaissace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                acteNaissaceKeyTyped(evt);
            }
        });

        datenaiss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datenaissKeyTyped(evt);
            }
        });

        jLabel11.setText("Situation Familiale");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Célibataire", "Marié(é)", "Veuf/ Veuve" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });

        jLabel12.setText("Lieu de naissance");

        jLabel7.setText("Prénom");

        Sexe.setText("Sexe");

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculin", "Féminin" }));
        cmbGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGenderActionPerformed(evt);
            }
        });

        jLabel8.setText("Domicile");

        jLabel9.setText("Nationalité");

        jLabel10.setText("Condamnation(s) : ");

        btnAjoutCondamnation.setText("Ajouter une condamnation");
        btnAjoutCondamnation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutCondamnationActionPerformed(evt);
            }
        });

        tableCondamnation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date de Condamnation", "Cours ou Trubinaux", "Nature des crimes ou délits", "Nature et durée de peine"
            }
        ));
        tableCondamnation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCondamnationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCondamnation);

        moisnaiss.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre" }));

        annenaiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annenaissActionPerformed(evt);
            }
        });
        annenaiss.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                annenaissKeyTyped(evt);
            }
        });

        jLabel14.setText("delivré le");

        jourAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jourActKeyTyped(evt);
            }
        });

        moisAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre" }));
        moisAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moisActActionPerformed(evt);
            }
        });

        anneAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anneActActionPerformed(evt);
            }
        });
        anneAct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                anneActKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout AjoutFormLayout = new javax.swing.GroupLayout(AjoutForm);
        AjoutForm.setLayout(AjoutFormLayout);
        AjoutFormLayout.setHorizontalGroup(
            AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AjoutFormLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AjoutFormLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(btnAjoutCondamnation, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257))
                    .addComponent(jScrollPane1)
                    .addGroup(AjoutFormLayout.createSequentialGroup()
                        .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel7)
                            .addComponent(Sexe)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(54, 54, 54)
                        .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pere)
                            .addComponent(mere)
                            .addComponent(lieunais)
                            .addComponent(Profession)
                            .addComponent(Domicile)
                            .addComponent(NomPers)
                            .addComponent(PrenomPers)
                            .addGroup(AjoutFormLayout.createSequentialGroup()
                                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nationalite, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AjoutFormLayout.createSequentialGroup()
                                        .addComponent(datenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(moisnaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(annenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(AjoutFormLayout.createSequentialGroup()
                                        .addComponent(acteNaissace, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jourAct, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(moisAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(anneAct, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(21, 21, 21))
        );
        AjoutFormLayout.setVerticalGroup(
            AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AjoutFormLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(acteNaissace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jourAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moisAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anneAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NomPers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PrenomPers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(datenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(moisnaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(annenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AjoutFormLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lieunais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sexe)
                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Profession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Domicile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nationalite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(AjoutFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(btnAjoutCondamnation))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnNouveau.setText("Nouveau");
        btnNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNouveauActionPerformed(evt);
            }
        });

        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btnEffacer.setText("Effacer");
        btnEffacer.setEnabled(false);
        btnEffacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerActionPerformed(evt);
            }
        });

        btnMaj.setText("Mettre à jour");
        btnMaj.setEnabled(false);
        btnMaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMajActionPerformed(evt);
            }
        });

        btnGetInfo.setText("Informations");
        btnGetInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetInfoActionPerformed(evt);
            }
        });

        btnImprimer.setText("Imprimer");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnImprimerActionPerformed(evt);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGetInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(btnMaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEffacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNouveau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNouveau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnregistrer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEffacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMaj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGetInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimer)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("DialogInput", 1, 13)); // NOI18N
        jLabel13.setText("Ajout ou modification des informations d'une personne - Casier Judiciaire N°3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(AjoutForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AjoutForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        AjoutForm.getAccessibleContext().setAccessibleName("Information sur le concerné");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Créer une nouvelle formulaire
    private void btnNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNouveauActionPerformed
    Reset();
    }//GEN-LAST:event_btnNouveauActionPerformed

    private boolean fieldsValidate(){
        if (acteNaissace.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Acte de naissance non initialisé", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        if (jourAct.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "veuillez ajouter le jour de creation  de l'acte de naissance", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (anneAct.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "veuillez ajouter le année de creation  de l'acte de naissance", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (NomPers.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le nom", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;

        }
        if (pere.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le nom du père", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (mere.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le nom de la mère", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Lieu de Naissance
        if (lieunais.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez ajouter un date de naissance", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cmbStatus.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner la situation familiale", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (Domicile.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez ajouter la domicile", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (Profession.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Veuillez ajouter une profession", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalDate.of(Integer.parseInt(anneAct.getText()), moisAct.getSelectedIndex() + 1, Integer.parseInt(jourAct.getText()));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Veuillez rectifier la date de délivrance de l'acte de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            LocalDate.of(Integer.parseInt(annenaiss.getText()), moisnaiss.getSelectedIndex() + 1, Integer.parseInt(datenaiss.getText()));
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Veuillez rectifier la date de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * ajout d'un nouvelle Personne
     * @param evt
     */

    // Enregistrer les données
    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
            if(!fieldsValidate()) {
                return;
            }
            try {
                Integer.parseInt(datenaiss.getText().strip());
                Integer.parseInt(annenaiss.getText().strip());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, e);
                return;
            }
            String status = "Célibataire";
            if (cmbStatus.getSelectedIndex() == 0) {
                status = "Célibataire";
            } else if (cmbStatus.getSelectedIndex() == 1) {
                status = cmbGender.getSelectedIndex() == 0 ? "Marié" : "Mariée";
            } else if (cmbStatus.getSelectedIndex() == 2) {
                status = cmbGender.getSelectedIndex() == 0 ? "Veuf" : "Veuve";
            }
            this.infoConsernedService = InfoConsernedService.getInstance();
            this.condamnationService = CondamnationService.getInstance();
            //formatage de la date de naissance
            LocalDate localDate = LocalDate.of(Integer.parseInt(annenaiss.getText()), moisnaiss.getSelectedIndex() + 1, Integer.parseInt(datenaiss.getText()));
            Date date = Date.valueOf(localDate);
            //formatage de la date pour l'acte de naissance
            LocalDate localActDate = LocalDate.of(Integer.parseInt(anneAct.getText()), moisAct.getSelectedIndex() + 1, Integer.parseInt(jourAct.getText()));
            Date dateAct = Date.valueOf(localActDate);
            InfoConserned infoConserned1 = new InfoConserned();
            infoConserned1.setActeNaissance(Integer.parseInt(acteNaissace.getText().strip()));
            infoConserned1.setDateActeNaissance(dateAct);
            infoConserned1.setNom(NomPers.getText().strip());
            infoConserned1.setPrenoms(PrenomPers.getText().strip());
            infoConserned1.setPere(pere.getText().strip());
            infoConserned1.setMere(mere.getText().strip());
            infoConserned1.setDateNaissance(date);
            infoConserned1.setLieuNaissance(Capitalize(lieunais.getText()).strip());
            infoConserned1.setProfession(Capitalize(Profession.getText()).strip());
            infoConserned1.setDomicile(Capitalize(Domicile.getText()).strip());
            infoConserned1.setNationalite(Nationalite.getText().strip().toUpperCase());
            infoConserned1.setSexe(cmbGender.getSelectedItem().toString());
            infoConserned1.setSituationFamiliale(status);
            for (Condamnation c :listCondamnationAdded ){
                infoConserned1.addCondamnation(c);
            }
            if(infoConsernedService.getInfoConsernedByAN(infoConserned1.getActeNaissance())!=null){
                JOptionPane.showMessageDialog(null, "Une personne avec la m�me numero d'acte de naissance" +
                        "existe déjà dans la base de données","Erreur",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(infoConsernedService.addConserned(infoConserned1)) {
                JOptionPane.showMessageDialog(null, "Enregistrement réussit");
                this.setVisible(false);
                ListePersonne.getInstance().infoConsernedList = infoConsernedService.getConsernedList();
                ListePersonne.getInstance().Get_Data();
                ListePersonne.getInstance().setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Echec de l'enregistement");
            }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnEffacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerActionPerformed
        int jOptionPane = JOptionPane.showConfirmDialog(null,"Voulez vous supprimer "+infoConserned.getNom()
        +" de la base de donnés?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(jOptionPane == 0){
            infoConsernedService = InfoConsernedService.getInstance();
            infoConsernedService.removeInfoConserned(infoConserned);
            JOptionPane.showMessageDialog(null,"Supression réuissit","Succès",JOptionPane.INFORMATION_MESSAGE);

            this.setVisible(false);
            ListePersonne.getInstance().infoConsernedList = infoConsernedService.getConsernedList();
            ListePersonne.getInstance().Get_Data();
            ListePersonne.getInstance().setVisible(true);
            ListePersonne.getInstance().requestFocus();
            this.dispose();
        }
    }//GEN-LAST:event_btnEffacerActionPerformed

    private void btnGetInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetInfoActionPerformed
        if(!fieldsValidate()){ return;}
        try {
            Integer.parseInt(datenaiss.getText().strip());
            Integer.parseInt(annenaiss.getText().strip());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        String status = "Célibataire";
        if (cmbStatus.getSelectedIndex() == 0) {
            status = "Célibataire";
        } else if (cmbStatus.getSelectedIndex() == 1) {
            status = cmbGender.getSelectedIndex() == 0 ? "Marié" : "Mariée";
        } else if (cmbStatus.getSelectedIndex() == 2) {
            status = cmbGender.getSelectedIndex() == 0 ? "Veuf" : "Veuve";
        }
        this.infoConsernedService = InfoConsernedService.getInstance();
        InfoConserned infoConserned1 = new InfoConserned();
        //formatage de la date pour l'acte de naissance
        try {
            LocalDate localActDate = LocalDate.of(Integer.parseInt(anneAct.getText()), moisAct.getSelectedIndex() + 1, Integer.parseInt(jourAct.getText()));
            Date dateAct = Date.valueOf(localActDate);
            infoConserned1.setDateActeNaissance(dateAct);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Veuillez rectifier la date de delivrence de l'acte de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //formatage de la date de naissance
        try {
            LocalDate localDate = LocalDate.of(Integer.parseInt(annenaiss.getText()), moisnaiss.getSelectedIndex() + 1, Integer.parseInt(datenaiss.getText()));
            Date date = Date.valueOf(localDate);
            infoConserned1.setDateNaissance(date);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Veuillez rectifier la date de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return;
        }
        infoConserned1.setActeNaissance(Integer.parseInt(acteNaissace.getText().strip()));
        infoConserned1.setNom(NomPers.getText().strip());
        infoConserned1.setPrenoms(PrenomPers.getText().strip());
        infoConserned1.setPere(pere.getText().strip());
        infoConserned1.setMere(mere.getText().strip());
        infoConserned1.setLieuNaissance(Capitalize(lieunais.getText()).strip());
        infoConserned1.setProfession(Capitalize(Profession.getText()).strip());
        infoConserned1.setDomicile(Capitalize(Domicile.getText()).strip());
        infoConserned1.setNationalite(Nationalite.getText().strip().toUpperCase());
        infoConserned1.setSexe(cmbGender.getSelectedItem().toString());
        infoConserned1.setSituationFamiliale(status);
        InfoConserned verif = infoConsernedService.getInfoConsernedByAN(infoConserned1.getActeNaissance());
        if(verif != null && verif != this.infoConserned ){
            JOptionPane.showMessageDialog(null, "Une personne avec la m�me numero d'acte de naissance" +
                    "existe déjà dans la base de données");
            return;
        }
        for (Condamnation c : listeDeCondamnations){
            infoConserned1.addCondamnation(c);
        }
            InfoPersonne frm=new InfoPersonne(infoConserned1);
            frm.setVisible(true);
    }//GEN-LAST:event_btnGetInfoActionPerformed

    /**
     * @description mise à jour des informations sur la conserné
     * @param evt
     */

    private void btnMajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMajActionPerformed
        if(!fieldsValidate()){ return;}
        try {
            Integer.parseInt(datenaiss.getText().strip());
            Integer.parseInt(annenaiss.getText().strip());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        String status = "Célibataire";
        if (cmbStatus.getSelectedIndex() == 0) {
            status = "Célibataire";
        } else if (cmbStatus.getSelectedIndex() == 1) {
            status = cmbGender.getSelectedIndex() == 0 ? "Marié" : "Mariée";
        } else if (cmbStatus.getSelectedIndex() == 2) {
            status = cmbGender.getSelectedIndex() == 0 ? "Veuf" : "Veuve";
        }
        this.infoConsernedService = InfoConsernedService.getInstance();
        LocalDate localDate = LocalDate.of(Integer.parseInt(annenaiss.getText()), moisnaiss.getSelectedIndex() + 1, Integer.parseInt(datenaiss.getText()));
        Date date = Date.valueOf(localDate);
        LocalDate localActDate = LocalDate.of(Integer.parseInt(anneAct.getText()), moisAct.getSelectedIndex() + 1, Integer.parseInt(jourAct.getText()));
        Date dateAct = Date.valueOf(localActDate);
        InfoConserned infoConserned1 = new InfoConserned();
        infoConserned1.setActeNaissance(Integer.parseInt(acteNaissace.getText().strip()));
        infoConserned1.setDateActeNaissance(dateAct);
        infoConserned1.setNom(NomPers.getText().strip());
        infoConserned1.setPrenoms(PrenomPers.getText().strip());
        infoConserned1.setPere(pere.getText().strip());
        infoConserned1.setMere(mere.getText().strip());
        infoConserned1.setDateNaissance(date);
        infoConserned1.setLieuNaissance(Capitalize(lieunais.getText()).strip());
        infoConserned1.setProfession(Capitalize(Profession.getText()).strip());
        infoConserned1.setDomicile(Capitalize(Domicile.getText()).strip());
        infoConserned1.setNationalite(Nationalite.getText().strip().toUpperCase());
        infoConserned1.setSexe(cmbGender.getSelectedItem().toString());
        infoConserned1.setSituationFamiliale(status);
        //ajout d'une nouvelle condamnation
        for(Condamnation c : listeDeCondamnations){
            c.setInfoConserned(null);
            infoConserned1.addCondamnation(c);
        }
        InfoConserned verif = infoConsernedService.getInfoConsernedByAN(infoConserned1.getActeNaissance());
        if(verif != null && verif != this.infoConserned ){
            JOptionPane.showMessageDialog(null, "Une personne avec la même numéro d'acte de naissance" +
                    "existe déjà dans la base de données");
            return;
        }
        int majConfirmation = JOptionPane.showConfirmDialog(null,"Voulez vous mettre à jour les informations sur "+infoConserned.getNom()
        + " ?","Confirmation",JOptionPane.YES_NO_OPTION);
        if(majConfirmation == 0){
        if(infoConsernedService.updateInfoConserned(infoConserned.getIdConserned(),infoConserned1)) {
            JOptionPane.showMessageDialog(null,"La mise à jour terminée avec succès");
            this.setVisible(false);
            ListePersonne.getInstance().infoConsernedList = infoConsernedService.getConsernedList();
            ListePersonne.getInstance().Get_Data();
            ListePersonne.getInstance().setVisible(true);
            ListePersonne.getInstance().requestFocus();
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Echec de la mise à jour");
        }
        }else{
            JOptionPane.showMessageDialog(null,"Mise à jour non enregisté");
        }

    }//GEN-LAST:event_btnMajActionPerformed


    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) throws Exception {//GEN-FIRST:event_btnImprimerActionPerformed
        if(!fieldsValidate()){ return;}
        try {
            Integer.parseInt(datenaiss.getText().strip());
            Integer.parseInt(annenaiss.getText().strip());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        String status = "Célibataire";
        if (cmbStatus.getSelectedIndex() == 0) {
            status = "Célibataire";
        } else if (cmbStatus.getSelectedIndex() == 1) {
            status = cmbGender.getSelectedIndex() == 0 ? "Marié" : "Mariée";
        } else if (cmbStatus.getSelectedIndex() == 2) {
            status = cmbGender.getSelectedIndex() == 0 ? "Veuf" : "Veuve";
        }
        this.infoConsernedService = InfoConsernedService.getInstance();
        this.condamnationService = CondamnationService.getInstance();
        InfoConserned infoConserned1 = new InfoConserned();
        //formatage de la date pour l'acte de naissance
        try {
            LocalDate localActDate = LocalDate.of(Integer.parseInt(anneAct.getText()), moisAct.getSelectedIndex() + 1, Integer.parseInt(jourAct.getText()));
            Date dateAct = Date.valueOf(localActDate);
            infoConserned1.setDateActeNaissance(dateAct);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Veuillez rectifier la date de delivrence de l'acte de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //formatage de la date de naissance
        try {
            LocalDate localDate = LocalDate.of(Integer.parseInt(annenaiss.getText()), moisnaiss.getSelectedIndex() + 1, Integer.parseInt(datenaiss.getText()));
            Date date = Date.valueOf(localDate);
            infoConserned1.setDateNaissance(date);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"Veuillez rectifier la date de naissance","Erreur",JOptionPane.ERROR_MESSAGE);
            return;
        }


        infoConserned1.setActeNaissance(Integer.parseInt(acteNaissace.getText().strip()));
        infoConserned1.setNom(NomPers.getText().strip());
        infoConserned1.setPrenoms(PrenomPers.getText().strip());
        infoConserned1.setPere(pere.getText().strip());
        infoConserned1.setMere(mere.getText().strip());
        infoConserned1.setLieuNaissance(Capitalize(lieunais.getText()).strip());
        infoConserned1.setProfession(Capitalize(Profession.getText()).strip());
        infoConserned1.setDomicile(Capitalize(Domicile.getText()).strip());
        infoConserned1.setNationalite(Nationalite.getText().strip().toUpperCase());
        infoConserned1.setSexe(cmbGender.getSelectedItem().toString());
        infoConserned1.setSituationFamiliale(status);

        InfoConserned verif = infoConsernedService.getInfoConsernedByAN(infoConserned1.getActeNaissance());
        if(verif != null && verif != this.infoConserned ){
            JOptionPane.showMessageDialog(null, "Une personne avec la m�me numero d'acte de naissance" +
                    "existe déjà dans la base de données");
            return;
        }
        for (Condamnation c : listeDeCondamnations){
            infoConserned1.addCondamnation(c);
        }
        if(PrinterService.Print(infoConserned1)){
            JOptionPane.showMessageDialog(null,"Impression terminée avec succès");
        }else{
            JOptionPane.showMessageDialog(null,"Impression annulée");
        }
    }//GEN-LAST:event_btnImprimerActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGenderActionPerformed

    private void btnAjoutCondamnationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutCondamnationActionPerformed
        tableCondamnation.setEnabled(false);
        AjoutCondamnation frm = new AjoutCondamnation();
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnAjoutCondamnationActionPerformed

    private void tableCondamnationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCondamnationMouseClicked
            try{
                int row= tableCondamnation.getSelectedRow();
                Condamnation condamnationClicked = listeDeCondamnations.get(row);
                tableCondamnation.setEnabled(false);
                AjoutCondamnation frm = new AjoutCondamnation(condamnationClicked);
                frm.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex);
            }

    }//GEN-LAST:event_tableCondamnationMouseClicked

    private void annenaissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annenaissActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annenaissActionPerformed

    private void acteNaissaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acteNaissaceKeyTyped
            char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
    } 
    }//GEN-LAST:event_acteNaissaceKeyTyped

    private void datenaissKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datenaissKeyTyped
            char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
    }
    }//GEN-LAST:event_datenaissKeyTyped

    private void annenaissKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_annenaissKeyTyped
            char c=evt.getKeyChar();
      if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))){
          getToolkit().beep();
          evt.consume();
    } 
    }//GEN-LAST:event_annenaissKeyTyped

    private void anneActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anneActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anneActActionPerformed

    private void jourActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jourActKeyTyped
        char c=evt.getKeyChar();
        if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jourActKeyTyped

    private void anneActKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anneActKeyTyped
            char c=evt.getKeyChar();
            if (!(Character.isDigit(c)|| (c== KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE))) {
                getToolkit().beep();
                evt.consume();
            }
    }//GEN-LAST:event_anneActKeyTyped

    private void moisActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moisActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moisActActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutPersonne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutPersonne().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AjoutForm;
    public javax.swing.JTextField Domicile;
    public javax.swing.JTextField Nationalite;
    public javax.swing.JTextField NomPers;
    public javax.swing.JTextField PrenomPers;
    public javax.swing.JTextField Profession;
    private javax.swing.JLabel Sexe;
    public javax.swing.JTextField acteNaissace;
    private javax.swing.JTextField anneAct;
    private javax.swing.JTextField annenaiss;
    private javax.swing.JButton btnAjoutCondamnation;
    public javax.swing.JButton btnEffacer;
    public javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnGetInfo;
    private javax.swing.JButton btnImprimer;
    public javax.swing.JButton btnMaj;
    private javax.swing.JButton btnNouveau;
    public javax.swing.JComboBox cmbGender;
    public javax.swing.JComboBox cmbStatus;
    public javax.swing.JTextField datenaiss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jourAct;
    public javax.swing.JTextField lieunais;
    public javax.swing.JTextField mere;
    private javax.swing.JComboBox<String> moisAct;
    private javax.swing.JComboBox<String> moisnaiss;
    public javax.swing.JTextField pere;
    private static javax.swing.JTable tableCondamnation;
    // End of variables declaration//GEN-END:variables
}
