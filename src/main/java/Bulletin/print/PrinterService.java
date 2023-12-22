package Bulletin.print;

import Bulletin.persistence.condamnation.Condamnation;
import Bulletin.persistence.infoCondamnation.InfoConserned;
import com.itextpdf.commons.utils.FileUtil;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class PrinterService {
    public static final String MASTERPATH = String.valueOf(PrinterService.class.getClassLoader().getResource("PdfTemplates"));

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
            return jour + " " + mois + " " + annee;
    }

    public static boolean Print(InfoConserned infoConserned) throws Exception{
        PdfReader pdfReader = new PdfReader(MASTERPATH+"/Casier-B3.pdf");
        // Créer un objet JFileChooser
        JFileChooser chooser = new JFileChooser("/run/media/dazai/NANIKA/teste");
        chooser.setSelectedFile(new File(chooser.getCurrentDirectory().toString()+"/Casier-B3-"+infoConserned.getNom()+".pdf"));
        // Choisir le dossier d'exportation
        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            PdfWriter pdfWriter = new PdfWriter(chooser.getSelectedFile());
            PdfDocument pdfDocument = new PdfDocument(pdfReader, pdfWriter);
            String name = infoConserned.getNom().strip() + " " + infoConserned.getPrenoms().strip();
            String pere = infoConserned.getPere();
            String mere = infoConserned.getMere();
            String dateNaiss = formatDate(infoConserned.getDateNaissance().toString(), "long");
            String lieuNaiss = infoConserned.getLieuNaissance();
            String sitFam = infoConserned.getSituationFamiliale();
            String profession = infoConserned.getProfession();
            String domicile = infoConserned.getDomicile();
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
            String localDate = LocalDate.now().toString();
            String dateAct = formatDate(infoConserned.getDateActeNaissance().toString(), "long");
            StringBuilder numeroPdf = new StringBuilder(String.valueOf(PrinterEntityService.getInstance().getLastId() + 1));
            while (numeroPdf.length() < 4) {
                numeroPdf.insert(0, "0");
            }
            if (!infoConserned.getCondamnations().isEmpty()) {
                Condamnation c1 = infoConserned.getCondamnations().get(0);
                dateCond = formatDate(c1.getDateCondamnation().toString(), "court");
                courOuTrubunal = c1.getCoursOutrubinaux();
                natureCrime = c1.getNatureCrime();
                naturePeine = c1.getNaturePeine();
                observation = c1.getObservation();
                if (infoConserned.getCondamnations().size() > 1) {
                    Condamnation c2 = infoConserned.getCondamnations().get(1);
                    dateCond2 = formatDate(c2.getDateCondamnation().toString(), "court");
                    courOuTrubunal2 = c2.getCoursOutrubinaux();
                    natureCrime2 = c2.getNatureCrime();
                    naturePeine2 = c2.getNaturePeine();
                    observation2 = c2.getObservation();
                }
            }


            PdfPage page = pdfDocument.getPage(1);
            PdfCanvas canvas = new PdfCanvas(page);

            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont(fontProgram);
            canvas.setFontAndSize(font, 11);

            canvas.beginText();
            canvas.setTextMatrix(343, 333);
            canvas.showText("");

            //Nom et Prenoms
            canvas.setTextMatrix(435, 642);
            if (name.length() > 22) {
                String[] splitedName = new String[2];
                int j = 0;
                for (int i = 0; i < name.length(); i++) {
                    if (name.toCharArray()[i] == ' ' && i < 23) {
                        splitedName[0] = name.substring(0, i).strip();
                        j = i;
                    } else {
                        splitedName[1] = name.substring(j, i + 1).strip();
                    }

                }
                canvas.setTextMatrix(435, 642);
                canvas.showText(splitedName[0]);
                canvas.setTextMatrix(282, 626);
                canvas.showText(splitedName[1]);
            } else {
                canvas.setTextMatrix(435, 642);
                canvas.showText(name.strip());
            }
            //Pere
            canvas.setTextMatrix(359, 609);
            canvas.showText(pere.strip());
            //Mere
            canvas.setTextMatrix(318, 592);
            canvas.showText(mere.strip());
            //Date de Naissance
            canvas.setTextMatrix(332, 576);
            canvas.showText(dateNaiss.strip());
            //Lieu de Naissance
            canvas.setTextMatrix(300, 560);
            canvas.showText(lieuNaiss.strip());
            //Situation Familiale
            canvas.setTextMatrix(387, 543);
            canvas.showText(sitFam.strip());
            //Profession
            canvas.setTextMatrix(340, 526);
            canvas.showText(profession.strip());
            //Domicile
            canvas.setTextMatrix(332, 510);
            canvas.showText(domicile.strip());
            //Nationalité
            canvas.setTextMatrix(346, 494);
            canvas.showText(nationalite.strip().toUpperCase());
            //Date de Condamnation
            if (!infoConserned.getCondamnations().isEmpty()) {

                canvas.setTextMatrix(75, 378);
                canvas.showText(dateCond.strip());
                //date de Condamnation 2
                if (dateCond2 != null) {
                    canvas.setTextMatrix(75, 356);
                    canvas.showText(dateCond2.strip());
                }
                //Cours ou Trubinaux
                canvas.setTextMatrix(155, 378);
                canvas.showText(courOuTrubunal.strip());
                //Cours ou Trubinaux 2
                if (courOuTrubunal2 != null) {
                    canvas.setTextMatrix(155, 356);
                    canvas.showText(courOuTrubunal2.strip());
                }
                //Nature de Crimes ou Delits
                canvas.setTextMatrix(254, 378);
                canvas.showText(natureCrime.strip());
                if (natureCrime2 != null) {
                    canvas.setTextMatrix(254, 356);
                    canvas.showText(natureCrime2.strip());
                }
                //Nature ou durée de peine
                canvas.setTextMatrix(351, 378);
                canvas.showText(naturePeine.strip());
                if (naturePeine2 != null) {
                    canvas.setTextMatrix(351, 356);
                    canvas.showText(naturePeine2.strip());
                }
                //Observation
                canvas.setTextMatrix(447, 378);
                canvas.showText(observation.strip());
                if (observation2 != null) {
                    canvas.setTextMatrix(447, 356);
                    canvas.showText(observation2.strip());
                }
            }
            //Acte de Naissance
            canvas.setTextMatrix(310, 334);
            canvas.showText(acteNaiss.strip());
            //Date acte de naissance
            canvas.setTextMatrix(390, 334);
            canvas.showText(dateAct);
            //Date de delivrance du casier judiciaire
            canvas.setTextMatrix(395, 262);
            canvas.showText(formatDate(localDate.strip(), "long"));
            //Numero pdf
            canvas.setTextMatrix(52, 642);
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
