package userinterface;

import database.WorkDB.ModeEnum;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 * Общий родительский класс для форм редактирования таблиц
 * @author hamster
 */
public class Edit extends javax.swing.JPanel {

   public Edit() {
      initComponents();
   }
   
   public Container getPnlContent() {
      return pnlContent;
   }
   
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      btnSave = new javax.swing.JButton();
      btnExit = new javax.swing.JButton();
      pnlContent = new javax.swing.JPanel();

      btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/save_l.png"))); // NOI18N
      btnSave.setText("Сохранить");
      btnSave.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSaveActionPerformed(evt);
         }
      });

      btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/exit_l.png"))); // NOI18N
      btnExit.setText("Выход");
      btnExit.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitActionPerformed(evt);
         }
      });

      pnlContent.setBorder(javax.swing.BorderFactory.createEtchedBorder());

      javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
      pnlContent.setLayout(pnlContentLayout);
      pnlContentLayout.setHorizontalGroup(
         pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );
      pnlContentLayout.setVerticalGroup(
         pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 3, Short.MAX_VALUE)
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnSave)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                  .addComponent(btnExit)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnExit)
               .addComponent(btnSave))
            .addGap(9, 9, 9))
      );
   }// </editor-fold>//GEN-END:initComponents

   private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      success = save();
      if (success) {
         UserInterface.saveSizeLocation(dialog, table.getTableName()+"Edit");
         dialog.setVisible(false);
      }
   }//GEN-LAST:event_btnSaveActionPerformed

   private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
      UserInterface.saveSizeLocation(dialog, table.getTableName()+"Edit");
      dialog.setVisible(false);
   }//GEN-LAST:event_btnExitActionPerformed

   protected boolean save() {
      return true;
   }
   
   // <editor-fold defaultstate="collapsed" desc="методы работы с полями класса">                          
   protected void setMode(ModeEnum m) {
      mode = m;
      switch (mode) {
         case INSERT:
            dialog.setTitle(dialog.getTitle()+".Добавление");
            break;
         case UPDATE:
            dialog.setTitle(dialog.getTitle()+".Редактирование");
            break;
         case FIND:
            dialog.setTitle(dialog.getTitle()+".Поиск");
            btnSave.setText("Поиск");
            btnSave.setIcon(new ImageIcon(getClass().getResource("/userinterface/find.png")));
            break;
      }      
   }
   
   public void setId(int i) {
      id = i;
   }
   
   public void setFld(Object[] values) {
   }
   
   public boolean isSuccess() {
      return success;
   }
   
   public void setDialog(JDialog d) {
      dialog = d;
   }
   
   public String getWhere() {
      return where;
   }
   
   public void setTable(Table t) {
      table = t;
      UserInterface.restoreSizeLocation(dialog, table.getTableName()+"Edit");
   }
   
   public Table getTable() {
      return table;
   }
   // </editor-fold>                        

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnExit;
   private javax.swing.JButton btnSave;
   private javax.swing.JPanel pnlContent;
   // End of variables declaration//GEN-END:variables
   
   /** Имя таблицы. */
   protected Table table = null;
   protected int id = -1;
   protected ModeEnum mode = null;
   protected boolean success = false;
   private JDialog dialog = null;
   protected String where = null;
}
