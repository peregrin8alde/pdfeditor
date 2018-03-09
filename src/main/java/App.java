
import pdf.PdfMng;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

public class App {

    private PdfMng pdfMng;

    public App() {
        pdfMng = new PdfMng();
    }

    public static void main(String[] args) {

        App app = new App();
        app.run();
    }

    public void run() {
        String[] inputFileNames = {
            "pdfBase.pdf",
            "pdfAdd.pdf"
        };
        String mergedFileName = "pdfMerged.pdf";

        try {
            pdfMng.merge(inputFileNames, mergedFileName);
            pdfMng.save(mergedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pdfMng.finish();
        }
    }
}
