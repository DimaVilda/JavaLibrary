package userinterface;

import database.WorkDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author hamster
 */
public class FormDic extends JPanel {

   public FormDic() {
      initComponents();
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      txtDic = new javax.swing.JTextField();
      btnDic = new javax.swing.JButton();

      btnDic.setText("jButton1");
      btnDic.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDicActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(txtDic, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(btnDic, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnDic)
               .addComponent(txtDic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
      );
   }// </editor-fold>//GEN-END:initComponents

   private void btnDicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDicActionPerformed
      JDialog dialog = new JDialog();
      try {
         Class cl = Class.forName(className);
         Table form = (Table) cl.newInstance();
         form.setWork(work);
         form.setDialog(dialog);
         form.setMode(Table.TableModeEnum.SELECT);
         //edit.setTable(this);
         dialog.setTitle(form.getTitle());
         //edit.setMode(WorkDB.ModeEnum.INSERT);
         dialog.add(form);
         dialog.setModal(true);
//         if (this.dialog.getIconImages().size() > 0) {
//            dialog.setIconImage(this.dialog.getIconImages().get(0));
//         }   
         dialog.setVisible(true);
         if (form.getId() != null) {
            value = form.getId();
            txtDic.setText(form.getMnemo());
         }
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
         Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }

   }//GEN-LAST:event_btnDicActionPerformed

   public String getClassName() {
      return className;
   }

   public void setClassName(String className) {
      this.className = className;
   }

   public Integer getValue() {
      return value;
   }

   public void setValue(Integer value) {
      this.value = value;
   }
   
   public void setValue(Integer value, String s) {
      this.value = value;
      txtDic.setText(s);
   }

   public void setWork(WorkDB work) {
      this.work = work;
   }
   

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnDic;
   private javax.swing.JTextField txtDic;
   // End of variables declaration//GEN-END:variables
   private String className = null;
   private Integer value = null;
   private WorkDB work = null;
}
