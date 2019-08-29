package userinterface;

import database.WorkDB;
import database.WorkDB.ModeEnum;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import report.ReportPDF;
import scannerbarcode.BarcodeReadEvent;
import scannerbarcode.BarcodeReadListener;

/**
 * Общий родительский класс для форм просмотра таблиц
 * @author hamster
 */
public class Table extends javax.swing.JPanel implements BarcodeReadListener {

   public Table() {
      initComponents();
      table.setSelectionModel(new DefaultListSelectionModel() {//чтоб выделать строки но не выделять пустую

         @Override 
         public void setSelectionInterval(int i, int i1) {
            Integer id = (Integer) table.getValueAt(i, table.getColumnCount()-1);
            if (id == null) {
               return;
            }
            super.setSelectionInterval(i, i1);
            activeRow = i;
            fireTableRecordChange(new TableRecordChangeEvent(table, i));
         }
         
      });
      scannerBarcode1.setForm(this);
   }
   
   public void query() {
      query(null);
   }
   
   public void query(String where) {
      clearTable(0);
      
      java.util.List<Object[]> list = null;
      if (where == null || where.isEmpty()) {
         list = work.selectTable(tableName, flds);
      } else {
         list = work.selectTable(tableName, flds, where);
      }
      int row = 0;
      for (Object[] values: list) {
         for (int i = 0; i < values.length; i++) {
            table.setValueAt(values[i], row, i);
         }
         row++;
      }
   }
   
   protected void clearTable(int row) {
      for (int r = row; r < table.getRowCount(); r++) {
         for (int i = 0; i < table.getColumnCount(); i++) {
            table.setValueAt(null, r, i);
         }
      }
   }
   
   public Container getPnlContent() {
      return pnlContent;
   }
   
   @Override
   public void readBarCode(BarcodeReadEvent e) { //поиск по штриху
      if (columnBarcode == -1) {
         return;
      }
      String barcode = e.getCode();
      if (barcode == null) {
         return;
      }
      long numCode = -1;
      try {
         numCode = Long.parseLong(barcode);
      } catch (NumberFormatException ex) {   
      }
      boolean ok = false;
      for (int row = 0; row < table.getModel().getRowCount(); row++) {
         Object num = table.getModel().getValueAt(row, columnBarcode);//значение кода в колонке
         if (num != null) {
            if (num instanceof String) {
               ok = num.equals(barcode);
            } else if (num instanceof Long) {
               ok = ((Long)num == numCode);
            }
            if (ok) {
               table.clearSelection();
               table.setSelectionMode(0);
               table.getSelectionModel().setSelectionInterval(row, row);
               btnEdit.doClick();
               break;
            }
         }
      }
      if (!ok){
         String st="Запись не найдена";
         JOptionPane.showMessageDialog(null,st);
      }
   }
  
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      lblTitle = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      table = new JTable() {
         @Override
         public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component component = super.prepareRenderer(renderer, row, column);
            TableColumn tableColumn = getColumnModel().getColumn(column);
            if (fldNames[column].trim().isEmpty()) {
               tableColumn.setPreferredWidth(0);
            }
            return component;
         }
      };
      btnAdd = new javax.swing.JButton();
      btnEdit = new javax.swing.JButton();
      btnDel = new javax.swing.JButton();
      btnClose = new javax.swing.JButton();
      pnlContent = new javax.swing.JPanel();
      btnFind = new javax.swing.JButton();
      scannerBarcode1 = new scannerbarcode.ScannerBarcode();
      btnReport = new javax.swing.JButton();

      lblTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

      table.addFocusListener(new java.awt.event.FocusAdapter() {
         public void focusGained(java.awt.event.FocusEvent evt) {
            tableFocusGained(evt);
         }
      });
      table.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableMouseClicked(evt);
         }
      });
      jScrollPane1.setViewportView(table);

      btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/add_l.png"))); // NOI18N
      btnAdd.setText("Добавить");
      btnAdd.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddActionPerformed(evt);
         }
      });

      btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/edit_l.png"))); // NOI18N
      btnEdit.setText("Редактировать ");
      btnEdit.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEditActionPerformed(evt);
         }
      });

      btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/delete_t.png"))); // NOI18N
      btnDel.setText("Удалить");
      btnDel.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDelActionPerformed(evt);
         }
      });

      btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/exit_l.png"))); // NOI18N
      btnClose.setText("Закрыть");
      btnClose.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCloseActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
      pnlContent.setLayout(pnlContentLayout);
      pnlContentLayout.setHorizontalGroup(
         pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );
      pnlContentLayout.setVerticalGroup(
         pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );

      btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/find.png"))); // NOI18N
      btnFind.setText("Поиск");
      btnFind.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFindActionPerformed(evt);
         }
      });

      scannerBarcode1.setDelay(200);
      scannerBarcode1.setInterval(1000);

      javax.swing.GroupLayout scannerBarcode1Layout = new javax.swing.GroupLayout(scannerBarcode1);
      scannerBarcode1.setLayout(scannerBarcode1Layout);
      scannerBarcode1Layout.setHorizontalGroup(
         scannerBarcode1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );
      scannerBarcode1Layout.setVerticalGroup(
         scannerBarcode1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );

      btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/print_l.png"))); // NOI18N
      btnReport.setText("Отчет");
      btnReport.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnReportActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGap(331, 331, 331))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(btnAdd)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnEdit)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnDel)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnFind)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(btnReport)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnClose)
                  .addContainerGap())
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(scannerBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                  .addContainerGap())))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
            .addGap(0, 0, 0)
            .addComponent(scannerBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(btnReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(btnAdd)
                  .addComponent(btnEdit)
                  .addComponent(btnDel)
                  .addComponent(btnClose)))
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

   private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      JDialog dialog = new JDialog();
      try {
         Class cl = Class.forName(classEdit);
         Edit edit = (Edit) cl.newInstance();
         
         edit.setDialog(dialog);
         edit.setTable(this);
         dialog.setTitle(title);
         edit.setMode(ModeEnum.INSERT);
         dialog.add(edit);
         dialog.setModal(true);
         if (this.dialog.getIconImages().size() > 0) {
            dialog.setIconImage(this.dialog.getIconImages().get(0));
         }   
         dialog.setVisible(true);
         if (edit.isSuccess()) {
            query();
         }
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
         Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }
   }//GEN-LAST:event_btnAddActionPerformed

   private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
      int row = table.getSelectedRow(); // узнаю какая строка выделенна
      if (row == -1) {
         return;
      }
      Integer id = (Integer) table.getValueAt(row, table.getColumnCount()-1);
      if (id == null) {
         return;
      }

      JDialog dialog = new JDialog();
      try {
         Class cl = Class.forName(classEdit);
         Edit edit = (Edit) cl.newInstance();
         
         edit.setDialog(dialog);
         edit.setTable(this);
         dialog.setTitle(title);
         edit.setMode(ModeEnum.UPDATE);

         edit.setId(id);

         Object[] values = new Object[table.getColumnCount()-1];
         for (int i = 0; i < values.length; i++) {
            values[i] = table.getValueAt(row, i);
         }
         edit.setFld(values);

         dialog.add(edit);
         dialog.setModal(true);
         if (this.dialog.getIconImages().size() > 0) {
            dialog.setIconImage(this.dialog.getIconImages().get(0));
         }   
         dialog.setVisible(true);
         if (edit.isSuccess()) {
            query();
         }
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
         Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }
   }//GEN-LAST:event_btnEditActionPerformed

   private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
      int row = table.getSelectedRow(); // узнаю какая строка выделенна
      if (row > -1) {
         Integer id = (Integer) table.getValueAt(row, table.getColumnCount()-1);
         if (id != null) {
            if (work.deleteTable(tableName, id)) {
               query();
            }
         }
      }
   }//GEN-LAST:event_btnDelActionPerformed

   private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
      UserInterface.saveSizeLocation(dialog, tableName, table);
      if (dialog != null) {
         dialog.setVisible(false);
      }   
   }//GEN-LAST:event_btnCloseActionPerformed

   private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
      table.getColumnModel().getColumn(0).setWidth(500);
     
      JDialog dialog = new JDialog();
      try {
         Class cl = Class.forName(classEdit);
         Edit edit = (Edit) cl.newInstance();
         
         edit.setDialog(dialog);
         dialog.setTitle(title);
         edit.setMode(ModeEnum.FIND);
         edit.setTable(this);
         dialog.add(edit);
         dialog.setModal(true);
         if (this.dialog.getIconImages().size() > 0) {
            dialog.setIconImage(this.dialog.getIconImages().get(0));
         }            dialog.setModal(true);

         dialog.setVisible(true);
         if (edit.isSuccess()) {
            query(edit.getWhere());
         }
      } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
         Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
      }

   }//GEN-LAST:event_btnFindActionPerformed

   private void tableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableFocusGained
      table.setSelectionMode(0);
      //System.out.println(table.getSelectedRowCount());
      int row = (activeRow > -1 ? activeRow : 0); 
      if (table.getRowCount() > 0) {
         table.getSelectionModel().setSelectionInterval(row, row);//выделение 1 колонки при запуске
      }   
   }//GEN-LAST:event_tableFocusGained

   private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
      if (table.getRowCount() == 0) {
         return;
      }
      int row = table.getSelectedRow();
      if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
         switch (mode) {
            case DEFAULT:
               btnEdit.doClick();
               break;
            case SELECT://если работает в режиме словаря ФормДик
               id = (Integer) table.getValueAt(row, table.getColumnCount()-1);
               for (int i = 0; i < flds.length; i++) {
                  if (flds[i].equals(mnemoFld)) {
                     mnemo = (String) table.getValueAt(row, i);
                  }
               }
               btnClose.doClick();
               break;
         }
      }
   }//GEN-LAST:event_tableMouseClicked

   private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
      ReportPDF rep = new ReportPDF(title, fldNames, table);
      rep.report();
   }//GEN-LAST:event_btnReportActionPerformed

   	// <editor-fold defaultstate="collapsed" desc="Генерация событий.">
	public void addTableRecordChangeListener(TableRecordChangeListener l) {
		listenerList.add(TableRecordChangeListener.class, l);
	}

	public void removeTableRecordChangeListener(TableRecordChangeListener l) {
		listenerList.remove(TableRecordChangeListener.class, l);
	}

	/**
	 * Генерация события 
	 * @param e Параметры события.
	 */
	public void fireTableRecordChange(TableRecordChangeEvent e) {
		EventListener[] listeners = listenerList.getListeners(
			TableRecordChangeListener.class);
		for (int i = 0; i < listeners.length; i++) {
			TableRecordChangeListener listener =
				(TableRecordChangeListener) listeners[i];
			listener.recordChange(e);
		}
	}
	// </editor-fold>


   // Variables declaration - do not modify//GEN-BEGIN:variables
   protected javax.swing.JButton btnAdd;
   private javax.swing.JButton btnClose;
   protected javax.swing.JButton btnDel;
   protected javax.swing.JButton btnEdit;
   private javax.swing.JButton btnFind;
   private javax.swing.JButton btnReport;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JLabel lblTitle;
   private javax.swing.JPanel pnlContent;
   private scannerbarcode.ScannerBarcode scannerBarcode1;
   protected javax.swing.JTable table;
   // End of variables declaration//GEN-END:variables

   // <editor-fold defaultstate="collapsed" desc="методы работы с полями класса">                          
   public void setTableName(String s) {
      tableName = s;
   }
   
   public String getTableName() {
      return tableName;
   }
   
   public void setFlds(String[] s) {
      flds = s;
   }
   
   public String[] getFlds() {
      return flds;
   }
   
   public void setFldNames(String[] s) {//отрисовка колонок в тбл
      fldNames = s;
      Object[][] mas = new Object [arow_count][s.length];//кол-во строк и столбцов
      Class[] tps = new Class [s.length];
      for (int i = 0; i < s.length; i++) {
         tps[i] = java.lang.String.class;
      }
      table.setModel(new javax.swing.table.DefaultTableModel(mas, s) {
         Class[] types = tps;
         boolean[] canEdit = new boolean [s.length];

         @Override
         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }

         @Override
         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
         }
      });      
   }
   
   public String[] getFldNames() {
      return fldNames;
   }
   
   public void setTitle(String s) {
      title = s;
      lblTitle.setText(s);
   }
   
   public String getTitle() {
      return title;
   }
   
   public void setClassEdit(String c) {
      classEdit = c;
   }
   
   public String getClassEdit() {
      return classEdit;
   }
   
   public void setDialog(JDialog dia) {
      dialog = dia;
      UserInterface.restoreSizeLocation(dialog, tableName, table);
      query();
   }
   
   public int getColumnBarcode() {
      return columnBarcode;
   }

   public void setColumnBarcode(int columnBarcode) {
      this.columnBarcode = columnBarcode;
   }

   public int getArow_count() {
      return arow_count;
   }

   public void setArow_count(int row_count) {
      this.arow_count = row_count;
   }

   public TableModeEnum getMode() {
      return mode;
   }

   public void setMode(TableModeEnum mode) {
      this.mode = mode;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getMnemo() {
      return mnemo;
   }

   public void setMnemo(String mnemo) {
      this.mnemo = mnemo;
   }

   public String getMnemoFld() {
      return mnemoFld;
   }

   public void setMnemoFld(String mnemoFld) {
      this.mnemoFld = mnemoFld;
   }

   public JTable getTable() {
      return table;
   }

   public void setWork(WorkDB work) {
      this.work = work;
   }
   
   // </editor-fold>                        
   
   /** Имя таблицы. */
   private String tableName = null;
   /** Имена полей для отображения. */
   private String[] flds = null;
   /** Названия полей для отображения. */
   private String[] fldNames = null;
   /** Имя раздела. */
   private String title = null;
   /** Класс формы редактирования. */
   private String classEdit = "library.Edit";
   private JDialog dialog = null;
   private int columnBarcode = -1;
   /** Первоначальный размер таблицы. */
   private int arow_count = 0;
   /** Значения при выборе. */
   private Integer id = null;
   private String mnemo = null;
   private String mnemoFld = null;
   private int activeRow = -1;
   /** Режим работы. */
   private TableModeEnum mode = TableModeEnum.DEFAULT;
   public enum TableModeEnum {
      DEFAULT, 
      /** Режим выбора записи в словаре. */
      SELECT;
   }
   private WorkDB work = null;
}
