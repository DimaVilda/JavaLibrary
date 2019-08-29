package userinterface;

import database.WorkDB;
import java.util.List;

/**
 * Родительский класс словаря
 * @author hamster
 */
public class ComboDic extends javax.swing.JPanel {

   public ComboDic() {
      initComponents();
   }
   
   private void init() {
      List<Object[]> list = work.selectTable(table
              , new String[] {field, "id"}, where);
      ids = new int[list.size()];
      int i = 0;
      combobox.addItem(null);
      for (Object[] values: list) {
         combobox.addItem(values[0]);
         ids[i++] = (int) values[1];
      }
   }

   /**
    * установить значение 
    * @param id - идентификатор таблиці
    */
   public void setValue(Integer id) {
//      if (ids == null) {
//         init();
//      }
      if (id == null) {
         return;
      }
      for (int i = 0; i < ids.length; i++) {
         if (id == ids[i]) {
            combobox.setSelectedIndex(i+1);
            break;
         }
      }
   }
   
   /**
    * получить значение
    * @return 
    */
   public Integer getValue() {
      Integer value = null;
      int ind = combobox.getSelectedIndex();
      if (ind > 0) {
         value = ids[ind-1];
      }
      return value;
   }
   
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      combobox = new javax.swing.JComboBox();

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(combobox, 0, 192, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(combobox)
      );
   }// </editor-fold>//GEN-END:initComponents


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox combobox;
   // End of variables declaration//GEN-END:variables
   
   // <editor-fold defaultstate="collapsed" desc="методы работы с полями класса">                          
   public void setTable(String s) {
      table = s;
   }
   
   public String getTable() {
      return table;
   }
   
   public void setField(String s) {
      field = s;
   }
   
   public String getField() {
      return field;
   }
   
   public void setWhere(String s) {
      where = s;
   }
   
   public String getWhere() {
      return where;
   }

   public void setWork(WorkDB work) {
      this.work = work;
      //if (ids == null) {
         init();
      //}
   }
   
   // </editor-fold>                        
   
   /** Имя таблиці. */
   private String table = null;
   /** Имя поля для отображения. */
   private String field = null;
   /** Условие. */
   private String where = null;
   /** Список идентификаторов. */
   private int[] ids = null;
   private WorkDB work = null;
}
