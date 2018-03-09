package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfMng {

    private HashMap<String, PDDocument> docMap;

    public PdfMng() {
        docMap = new HashMap<String, PDDocument>();
    }

    public PDDocument open(String fileName) throws IOException {
        PDDocument pdfdoc = PDDocument.load(new File(fileName));
        docMap.put(fileName, pdfdoc);

        return pdfdoc;
    }

    public void close(String fileName) throws IOException {
        PDDocument pdfdoc = docMap.get(fileName);
        pdfdoc.close();
        docMap.remove(fileName);
    }

    public void finish() {
        for(Map.Entry<String, PDDocument> entry : docMap.entrySet()) {
            String key = entry.getKey();
            PDDocument pdfdoc = entry.getValue();
            try {
                pdfdoc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        docMap.clear();
    }

    public PDDocument merge(String[] inputFileNames, String outputFileName) throws IOException {
        PDDocument resultDoc = new PDDocument();

        for (String fileName : inputFileNames) {
            PDDocument pdfdoc = docMap.get(fileName);
            if (pdfdoc == null) {
                pdfdoc = open(fileName);
            }

            pdfdoc.getPages().forEach(page -> resultDoc.addPage(page));
        }
        docMap.put(outputFileName, resultDoc);

        return resultDoc;
    }

    public void save(String fileName) throws IOException {
        PDDocument pdfdoc = docMap.get(fileName);
        pdfdoc.save(fileName);
    }
}
