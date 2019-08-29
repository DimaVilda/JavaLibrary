package userinterface;

import database.WorkDB;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author hamster
 */
public class Table2 extends JPanel implements TableRecordChangeListener {
   public Table2() {
      initComponents();
      table1.addTableRecordChangeListener(this);
   }
   
   @Override
   public void recordChange(TableRecordChangeEvent e) {
      Integer id = (Integer) table1.getTable().getValueAt(e.getRow(), 
              table1.getTable().getColumnCount()-1); //выбираю ид из тбл1
      if (id == null) {
         return;
      }
      String where = linkFld + " = '"+id+"'";
      table2.query(where);
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      table1 = new userinterface.Table();
      table2 = new userinterface.Table();

      table1.setArow_count(20);
      table1.setClassEdit("library.readers.ReaderEdit");
      table1.setColumnBarcode(3);
      table1.setFldNames(new String[] {"ФИО", "Адрес", "Телефон", "Номер карточки", ""});
      table1.setFlds(new String[] {"fio", "address", "phone", "card_number", "id"});
      table1.setMnemoFld("fio");
      table1.setTableName("readers");
      table1.setTitle("Читатели");

      javax.swing.GroupLayout table1Layout = new javax.swing.GroupLayout(table1.getPnlContent());
      table1.getPnlContent().setLayout(table1Layout);
      table1Layout.setHorizontalGroup(
         table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );
      table1Layout.setVerticalGroup(
         table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );

      table2.setArow_count(20);
      table2.setClassEdit("library.books.BookEdit");
      table2.setColumnBarcode(2);
      table2.setFldNames(new String[] {"Название", "Автор", "Инв.номер", "Жанр", "Кол-во экземпляров", "", ""});
      table2.setFlds(new String[] {"name", "autors", "inv_num", "domain_name", "count", "domains_id", "id"});
      table2.setMnemoFld("name");
      table2.setTableName("vlistbooks");
      table2.setTitle("Книги");

      javax.swing.GroupLayout table2Layout = new javax.swing.GroupLayout(table2.getPnlContent());
      table2.getPnlContent().setLayout(table2Layout);
      table2Layout.setHorizontalGroup(
         table2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 405, Short.MAX_VALUE)
      );
      table2Layout.setVerticalGroup(
         table2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(table1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(table2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(table1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(table2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

   public String getTitle() {
      return table1.getTitle();
   }
   
   public void setDialog(JDialog dia) {
      table1.setDialog(dia);
      table2.setDialog(dia);
   }

   public String getLinkFld() {
      return linkFld;
   }

   public void setLinkFld(String linkFld) {
      this.linkFld = linkFld;
   }

   /** Имя поля для связи таблиц. */
   private String linkFld = null;
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private userinterface.Table table1;
   private userinterface.Table table2;
   // End of variables declaration//GEN-END:variables

   public void setWork(WorkDB work) {
      table1.setWork(work);
      table2.setWork(work);
   }

}
