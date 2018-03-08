package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

public class PdfMng {

    public PDDocument open(String fileName) {
        PDDocument newDoc = null;

        try (PDDocument pdfdoc = PDDocument.load(new File(fileName))) {

            newDoc = pdfdoc;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newDoc;
    }

    public PDDocument merge(PDDocument pdfBase, PDDocument pdfAdd) {
        PDDocument pdfNew = null;
        try (PDDocument pdfdoc = new PDDocument()) {
            pdfBase.getPages().forEach(page -> pdfdoc.addPage(page));
            pdfAdd.getPages().forEach(page -> pdfdoc.addPage(page));
            
            pdfNew = pdfdoc;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pdfNew;
    }

    public void save(PDDocument pdf, String fileName) throws IOException {

        pdf.save(fileName);
    }
}
