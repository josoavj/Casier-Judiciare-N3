package Bulletin.print;

import Bulletin.persistence.condamnation.Condamnation;
import Bulletin.persistence.infoCondamnation.InfoConserned;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class PrinterService {
    public static final String MASTERPATH = String.valueOf(PrinterService.class.getClassLoader().getResource("PdfTemplates"));
    public static final String JOSEFIN = "src/main/resources/Fonts/josefin/JosefinSans-Regular.ttf";

    private static String formatDate(String date, String formatDate) {
        Pattern pattern = Pattern.compile("[ -]");
        String[] format = pattern.split(date);
        String jour = format[2];
        String mois = format[1];
        String annee = format[0];
        if (Objects.equals(formatDate, "long")) {
            switch (format[1]) {
                case "01":
                    mois = "Janvier";
                    break;
                case "02":
                    mois = "Fevrier";
                    break;
                case "03":
                    mois = "Mars";
                    break;
                case "04":
                    mois = "Avril";
                    break;
                case "05":
                    mois = "Mai";
                    break;
                case "06":
                    mois = "Juin";
                    break;
                case "07":
                    mois = "Juillet";
                    break;
                case "08":
                    mois = "A�ut";
                    break;
                case "09":
                    mois = "Septembre";
                    break;
                case "10":
                    mois = "Octobre";
                    break;
                case "11":
                    mois = "Novembre";
                    break;
                case "12":
                    mois = "Decembre";
                    break;
            }
        }
            return jour + "-" + mois + "-" + annee;
    }

    public static boolean Print(InfoConserned infoConserned) throws Exception{
        PdfReader pdfReader = new PdfReader(MASTERPATH+"/Casier-B3.pdf");
        // Créer un objet JFileChooser
        JFileChooser chooser = new JFileChooser("/run/media/dazai/NANIKA/teste");
        //JFileChooser chooser = new JFileChooser("/home/josoa/Documents/GitHub/Casier-Judiciare-N3");
        chooser.setSelectedFile(new File(chooser.getCurrentDirectory().toString()+"/Casier-B3-"+infoConserned.getNom()+".pdf"));
        // Choisir le dossier d'exportation
        int returnVal = chooser.showSaveDialog(null);
        boolean b = true;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            PdfWriter pdfWriter = new PdfWriter(chooser.getSelectedFile());
            //PdfWriter pdfWriter = new PdfWriter("src/main/resources/PdfTemplates/Casier-B3name.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);
            String name = infoConserned.getNom().strip() + " " + infoConserned.getPrenoms().strip();
            String pere = infoConserned.getPere();
            String mere = infoConserned.getMere();
            String dateNaiss = formatDate(infoConserned.getDateNaissance().toString(), "long");
            String lieuNaiss = String.valueOf(infoConserned.getLieuNaissance().charAt(0)).toUpperCase()+infoConserned.getLieuNaissance().substring(1);
            String sitFam = infoConserned.getSituationFamiliale();
            String profession = infoConserned.getProfession().toUpperCase().charAt(0)+infoConserned.getProfession().substring(1);
            String domicile = infoConserned.getDomicile().toUpperCase().charAt(0)+infoConserned.getDomicile().substring(1);
            String nationalite = infoConserned.getNationalite();
            String dateCond = null;
            String dateCond2 = null;
            String courOuTrubunal = null;
            String courOuTrubunal2 = null;
            String natureCrime = null;
            String natureCrime2 = null;
            String naturePeine = null;
            String naturePeine2 = null;
            String observation = null;
            String observation2 = null;
            String acteNaiss = String.valueOf(infoConserned.getActeNaissance());
            while (acteNaiss.length() < 4) {
                acteNaiss = "0" + acteNaiss;
            }
            String localDate = LocalDate.now().toString();
            String dateAct = formatDate(infoConserned.getDateActeNaissance().toString(), "long");
            StringBuilder numeroPdf = new StringBuilder(String.valueOf(PrinterEntityService.getInstance().getLastId() + 1));
            while (numeroPdf.length() < 4) {
                numeroPdf.insert(0, "0");
            }
            if (!infoConserned.getCondamnations().isEmpty()) {
                Condamnation c1 = infoConserned.getCondamnations().get(0);
                dateCond = formatDate(c1.getDateCondamnation().toString(), "court");
                courOuTrubunal = c1.getCoursOutrubinaux().toUpperCase();
                natureCrime = c1.getNatureCrime().toUpperCase().charAt(0)+c1.getNatureCrime().substring(1);
                naturePeine = c1.getNaturePeine().toUpperCase().charAt(0)+c1.getNaturePeine().substring(1);
                observation = c1.getObservation().toUpperCase().charAt(0)+c1.getObservation().substring(1);
                if (infoConserned.getCondamnations().size() > 1) {
                    Condamnation c2 = infoConserned.getCondamnations().get(1);
                    dateCond2 = formatDate(c2.getDateCondamnation().toString(), "court");
                    courOuTrubunal2 = c2.getCoursOutrubinaux();
                    natureCrime2 = c2.getNatureCrime().toUpperCase().charAt(0)+c2.getNatureCrime().substring(1);
                    naturePeine2 = c2.getNaturePeine().toUpperCase().charAt(0)+c2.getNaturePeine().substring(1);
                    observation2 = c2.getObservation().toUpperCase().charAt(0)+c2.getObservation().substring(1);
                }
            }


            PdfPage page = pdfDocument.getPage(1);
            PdfCanvas canvas = new PdfCanvas(page);

            FontProgram fontProgram = FontProgramFactory.createFont(JOSEFIN);
            PdfFont font = PdfFontFactory.createFont(fontProgram);
            canvas.setFontAndSize(font, 10);

            canvas.beginText();
            canvas.setTextMatrix(343, 333);
            canvas.showText("");

            //Nom et Prenoms
            if (name.length() > 30) {
                String[] splitedName = new String[2];
                int j = 0;
                for (int i = 0; i < name.length(); i++) {
                    if (name.toCharArray()[i] == ' ' && i < 31) {
                        splitedName[0] = name.substring(0, i).strip();
                        j = i;
                    } else {
                        splitedName[1] = name.substring(j, i + 1).strip();
                    }

                }
                canvas.setTextMatrix(386, 688);
                canvas.showText(splitedName[0]);
                canvas.setTextMatrix(246, 675);
                canvas.showText(splitedName[1]);
            } else {
                canvas.setTextMatrix(386, 688);
                canvas.showText(name.strip());
            }
            //Pere
            canvas.setTextMatrix(316, 658);
            canvas.showText(pere.strip());
            //Mere
            canvas.setTextMatrix(278, 642);
            canvas.showText(mere.strip());
            //Date de Naissance
            canvas.setTextMatrix(291, 627);
            canvas.showText(dateNaiss.strip());
            //Lieu de Naissance
            canvas.setTextMatrix(260, 611);
            canvas.showText(lieuNaiss.strip());
            //Situation Familiale
            canvas.setTextMatrix(342, 595);
            canvas.showText(sitFam.strip());
            //Profession
            canvas.setTextMatrix(298, 580);
            canvas.showText(profession.strip());
            //Domicile
            canvas.setTextMatrix(292, 565);
            canvas.showText(domicile.strip());
            //Nationalité
            canvas.setTextMatrix(305, 549);
            canvas.showText(nationalite.strip().toUpperCase());
            //Date de Condamnation
            if (!infoConserned.getCondamnations().isEmpty()) {

                canvas.setTextMatrix(42, 495);
                canvas.showText(dateCond.strip());
                //date de Condamnation 2
                if (dateCond2 != null) {
                    canvas.setTextMatrix(42, 470);
                    canvas.showText(dateCond2.strip());
                }
                //Cours ou Trubinaux
                canvas.setTextMatrix(115, 495);
                canvas.showText(courOuTrubunal.strip());
                //Cours ou Trubinaux 2
                if (courOuTrubunal2 != null) {
                    canvas.setTextMatrix(115, 470);
                    canvas.showText(courOuTrubunal2.strip());
                }
                //Nature de Crimes ou Delits
                if (natureCrime.strip().length() > 20) {
                    String[] splitedNatureCrime = new String[2];
                    int j = 0;
                    for (int i = 0; i < natureCrime.strip().length(); i++) {
                        if (natureCrime.strip().toCharArray()[i] == ' ' && i < 21) {
                            splitedNatureCrime[0] = natureCrime.substring(0, i).strip();
                            j = i;
                        } else {
                            splitedNatureCrime[1] = natureCrime.substring(j, i + 1).strip();
                        }

                    }
                    canvas.setTextMatrix(232, 495);
                    canvas.showText(splitedNatureCrime[0]);

                    canvas.setTextMatrix(232, 484);
                    if(splitedNatureCrime[1].length()>20){
                        splitedNatureCrime[1] = splitedNatureCrime[1].substring(0,19)+"...";
                    }
                    canvas.showText(splitedNatureCrime[1]);
                } else {
                    canvas.setTextMatrix(232, 495);
                    canvas.showText(natureCrime.strip());
                }
                if (natureCrime2 != null) {
                    canvas.setTextMatrix(232, 470);
                    if(natureCrime2.length()>20){
                        natureCrime2 = natureCrime2.substring(0,19)+"...";
                    }
                    canvas.showText(natureCrime2.strip());
                }
                //Nature ou durée de peine
                if (naturePeine.strip().length() > 18) {
                    String[] splitedNaturePeine = new String[2];
                    int j = 0;
                    for (int i = 0; i < naturePeine.strip().length(); i++) {
                        if (naturePeine.strip().toCharArray()[i] == ' ' && i < 19) {
                            splitedNaturePeine[0] = naturePeine.substring(0, i).strip();
                            j = i;
                        } else {
                            splitedNaturePeine[1] = naturePeine.substring(j, i + 1).strip();
                        }

                    }
                    canvas.setTextMatrix(345, 495);
                    canvas.showText(splitedNaturePeine[0]);

                    canvas.setTextMatrix(345, 484);
                    if(splitedNaturePeine[1].length()>18){
                        splitedNaturePeine[1] = splitedNaturePeine[1].substring(0,17)+"...";
                    }
                    canvas.showText(splitedNaturePeine[1]);
                } else {
                    canvas.setTextMatrix(345, 495);
                    canvas.showText(naturePeine.strip());
                }
                if (naturePeine2 != null) {
                    canvas.setTextMatrix(345, 470);
                    if(naturePeine2.length()>18){
                        naturePeine2 = naturePeine2.substring(0,17)+"...";
                    }
                    canvas.showText(naturePeine2.strip());
                }
                //Observation
                if (observation.strip().length() > 17) {
                    String[] splitedObservation = new String[2];
                    int j = 0;
                    for (int i = 0; i < observation.strip().length(); i++) {
                        if (observation.strip().toCharArray()[i] == ' ' && i < 18) {
                            splitedObservation[0] = observation.substring(0, i).strip();
                            j = i;
                        } else {
                            splitedObservation[1] = observation.substring(j, i + 1).strip();
                        }

                    }
                    canvas.setTextMatrix(447, 495);
                    canvas.showText(splitedObservation[0]);

                    canvas.setTextMatrix(447, 484);
                    if(splitedObservation[1].length()>17){
                        splitedObservation[1] = splitedObservation[1].substring(0,16)+"...";
                    }
                    canvas.showText(splitedObservation[1]);
                } else {
                    canvas.setTextMatrix(447, 495);
                    canvas.showText(observation.strip());
                }
                if (observation2 != null) {
                    canvas.setTextMatrix(447, 470);
                    if(observation2.length()>18){
                        observation2 = observation2.substring(0,16)+"...";
                    }
                    canvas.showText(observation2.strip());
                }
            }
            //Acte de Naissance
            canvas.setTextMatrix(252, 452);
            canvas.showText(acteNaiss.strip());
            //Date acte de naissance
            canvas.setTextMatrix(320, 452);
            canvas.showText(dateAct);
            //Date de delivrance du casier judiciaire
            canvas.setTextMatrix(387, 408);
            canvas.showText(formatDate(localDate.strip(), "long"));
            //Numero pdf
            canvas.setTextMatrix(49, 689);
            canvas.showText(numeroPdf.toString().strip());


            canvas.endText();
            pdfDocument.close();
           PrinterEntity printerEntity = new PrinterEntity(infoConserned.getNom());
           PrinterEntityService.getInstance().persistePrintedInfo(printerEntity);
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        InfoConserned infoConserned = Persistence.createEntityManagerFactory("Bulletin").createEntityManager().find(InfoConserned.class,2);
        if(infoConserned != null){
            Print(infoConserned);
        }
    }
}
