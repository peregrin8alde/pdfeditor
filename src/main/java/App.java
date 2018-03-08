
import pdf.PdfMng;

import org.apache.pdfbox.pdmodel.PDDocument;

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
        PDDocument pdfBase = pdfMng.open("pdfBase.pdf");
        PDDocument pdfAdd = pdfMng.open("pdfAdd.pdf");
        
        PDDocument pdfMerge = pdfMng.merge(pdfBase, pdfAdd);
    }
}
