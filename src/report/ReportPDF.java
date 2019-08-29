package report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author hamster
 */
public class ReportPDF {
 
   public ReportPDF(String t, String[] as, JTable tbl) {
      title = t;
      headers = as;
      table = tbl;
   }
    public class Rotate extends PdfPageEventHelper {
 
        protected PdfNumber orientation = PdfPage.LANDSCAPE;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }
   
   public void report() {
      try {
         Document document = new Document(PageSize.A4, 10, 10, 50, 50);
         String path = System.getProperty("user.dir");
         File fileOut = new File(path+"\\"+file);
         fileOut.delete();
         PdfWriter writer = PdfWriter.getInstance(document, 
                 new FileOutputStream(fileOut));
         
         Rotate event = new Rotate();
         writer.setPageEvent(event);
         document.setPageSize(PageSize.LETTER.rotate());
         document.open();
         event.setOrientation(PdfPage.LANDSCAPE);
      BaseFont times =
            BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251",BaseFont.EMBEDDED);
      Paragraph pText = new Paragraph(title, new Font(times, 20, Font.BOLD));
      Chapter chapter1 = new Chapter(pText, 0);
      chapter1.setNumberDepth(0);         
      int colCount = 0;
      for (int i = 0; i < headers.length; i++) {
         if (!headers[i].isEmpty()) {
            colCount++;
         }
      }
      // Таблица
      PdfPTable t = new PdfPTable(colCount+1);
      t.setSpacingBefore(25);
      t.setWidthPercentage(100);
      //t.setSpacingAfter(25);
      // Ширина колонок
      int[] ws = new int[colCount+1];
      ws[0] = 30;
      for (int i = 1; i < ws.length; i++) {
         ws[i] = table.getColumnModel().getColumn(i-1).getWidth();
      }   
      t.setWidths(ws);
      
      // Заголоки колонок
      PdfPCell c = new PdfPCell(new Phrase("№ пп", new Font(times, 14, Font.BOLD)));  
      t.addCell(c);
      for (int i = 0; i < colCount; i++) {
         c = new PdfPCell(new Phrase(headers[i], new Font(times, 14, Font.BOLD)));
         t.addCell(c);
      }
      // Данные колонок
      for (int i = 0; i < table.getRowCount(); i++) {
         c = new PdfPCell(new Phrase(String.valueOf(i+1), new Font(times,14)));  
         t.addCell(c);
         boolean isNull = true;
         for (int j = 0; j < colCount; j++) {
            Object value = table.getValueAt(i, j);
            if (value != null) {
               isNull = false;
            } else {
               value = "";
            }
            String str = String.valueOf(value);
            c = new PdfPCell(new Phrase(str, new Font(times,14)));  
            t.addCell(c);
         }
         if (isNull) {
            t.deleteLastRow();
         }
      }
      // вывод итогов
      //
      chapter1.add(t);
       
      document.add(chapter1);
      document.close();
      
      if (fileOut.isFile()) {
         Desktop.getDesktop().open(fileOut);
      }   
      
      } catch (DocumentException ex) {
         Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
      } catch (FileNotFoundException ex) {
         Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
         Logger.getLogger(ReportPDF.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   /** Имя файла отчета. */
   private String file = "report.pdf";
   /** Заголовок отчета. */
   private String title = null;
   /** Массив заголовков для колонок таблиц */
   private String[] headers = null;
   /** Объект таблицы с данными. */
   private JTable table = null;
}
