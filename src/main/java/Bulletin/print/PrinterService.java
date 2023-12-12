package Bulletin.print;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class PrinterService {
    public static final String MASTERPATH = String.valueOf(PrinterService.class.getClassLoader().getResource("PdfTemplates"));
    public static void Print() throws Exception{
        PdfReader pdfReader = new PdfReader(MASTERPATH+"/Casier-B3.pdf");
        PdfWriter pdfWriter = new PdfWriter("src/main/resources/PdfTemplates/Casier-B3"+"name"+".pdf");
        PdfDocument pdfDocument = new PdfDocument(pdfReader,pdfWriter);

        PdfPage page = pdfDocument.getPage(1);
        PdfCanvas canvas = new PdfCanvas(page);

        FontProgram fontProgram = FontProgramFactory.createFont();
        PdfFont font = PdfFontFactory.createFont(fontProgram,"utf-8");
        /**canvas.setFontAndSize(font, 9);

        canvas.beginText();
        canvas.setTextMatrix(343,333);
        canvas.showText("VONJINIAIANA Josoa Pory");

        canvas.setTextMatrix(285,723);
        canvas.showText("VONJINIAIANA ");

        canvas.endText();**/
        pdfDocument.close();

    }
    public static void main(String[] args) throws Exception {
        Print();
    }
}
