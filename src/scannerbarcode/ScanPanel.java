package scannerbarcode;

import javax.swing.JPanel;

/**
 *
 * @author alexej
 */
public class ScanPanel extends JPanel implements BarcodeReadListener {

	public ScanPanel() {
		initComponents();
		scannerBarcode1.setForm(this);
	}

	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      btnClose = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      txtLog = new javax.swing.JTextArea();
      btnClear = new javax.swing.JButton();
      scannerBarcode1 = new scannerbarcode.ScannerBarcode();

      btnClose.setText("Закрыть");
      btnClose.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCloseActionPerformed(evt);
         }
      });

      txtLog.setEditable(false);
      txtLog.setColumns(20);
      txtLog.setRows(5);
      jScrollPane1.setViewportView(txtLog);

      btnClear.setText("Очистить");
      btnClear.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnClearActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                     .addGap(14, 14, 14)
                     .addComponent(btnClear))
                  .addGroup(layout.createSequentialGroup()
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                     .addComponent(btnClose)))
               .addGroup(layout.createSequentialGroup()
                  .addGap(18, 18, 18)
                  .addComponent(scannerBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnClear)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(scannerBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnClose))
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

   private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
		System.exit(0);
   }//GEN-LAST:event_btnCloseActionPerformed

   private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
		txtLog.setText(null);
   }//GEN-LAST:event_btnClearActionPerformed


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnClear;
   private javax.swing.JButton btnClose;
   private javax.swing.JScrollPane jScrollPane1;
   private scannerbarcode.ScannerBarcode scannerBarcode1;
   private javax.swing.JTextArea txtLog;
   // End of variables declaration//GEN-END:variables

	@Override
	public void readBarCode(BarcodeReadEvent e) {
		String text = txtLog.getText();
		StringBuilder sb = new StringBuilder();
		if (text != null && !text.isEmpty()) {
			sb.append(text).append("\n");
		}
		sb.append(e.getCode());
		txtLog.setText(sb.toString());
	}
}
