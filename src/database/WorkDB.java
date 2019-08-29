package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hamster
 */
public class WorkDB {
   /**
    * Метод получения коннекта к БД 
    */
   public void getConnect(String user, String pass, String url, String db) {
      //Connection conn = null;
      url = "jdbc:postgresql://" + url + "/" + db; 
      try {
         Class.forName("org.postgresql.Driver").newInstance();
         conn = DriverManager.getConnection(url, user, pass);
      } catch (ClassNotFoundException | InstantiationException 
              | IllegalAccessException | SQLException ex) {
         Logger.getLogger(WorkDB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   public List<Object[]> selectTable(String name, String[] names) {
      return selectTable(name, names, null);
   }
   
   public List<Object[]> selectTable(String name, String[] names, String where) {
      //Connection conn = LibraryFrame.conn;//getConnect();
      List<Object[]> list = new ArrayList<Object[]>();
      if (conn != null) {
         try {
            Statement st = conn.createStatement();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            for (String fld : names) {
               sql.append(fld).append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(" FROM ").append(name);
            if (where != null) {
               sql.append(" WHERE ").append(where);
            }
            sql.append(" ORDER BY id");
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql.toString());
            while (rs.next()) {
               Object[] values = new Object[names.length];
               for (int i = 0; i < values.length; i++) {
                  values[i] = rs.getObject(names[i]);
               }
               list.add(values);
            }   
         } catch (SQLException ex) {
            Logger.getLogger(WorkDB.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      return list;
   }
   
   public boolean insertTable(String name, String[] names, Object[] values) {
      boolean ok = false;
      //Connection conn = LibraryFrame.conn;//getConnect();
      //Connection conn = getConnect();
      if (name.startsWith("v")) {
         name = name.substring(1);
      }
      if (conn != null) {
         try {
            Statement st = conn.createStatement();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(name).append(" (");
            for (String name1 : names) {
               sql.append(name1).append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(") VALUES (");
            
            for (Object value : values) {
               if (value instanceof String) {
                  if (((String)value).isEmpty()) {
                     value = null;
                     sql.append(value);
                  } else {
                     sql.append("'").append(value).append("'");
                  }
               } else if (value instanceof java.util.Date) {
                  sql.append("'").append(new java.sql.Date(((java.util.Date)value).getTime())).append("'");
               } else {
                  sql.append(value);
               }
               sql.append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(")");
            
            System.out.println(sql);
            ok = st.executeUpdate(sql.toString()) == 1;
         } catch (SQLException ex) {
            Logger.getLogger(WorkDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      }
      return ok;
   }
   
   public boolean deleteTable(String name, Integer id) {
      boolean ok = false;
      int selection = JOptionPane.showConfirmDialog(null, "Вы уверены?", 
              "Удаление записи", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (selection != JOptionPane.YES_OPTION) {
         return ok;
      }      
      if (name.startsWith("v")) {
         name = name.substring(1);
      }
      //Connection conn = LibraryFrame.conn;//getConnect();
      //Connection conn = getConnect();
      if (conn != null) {
         try {
            Statement st = conn.createStatement();
            String sql = "DELETE FROM " + name + " WHERE id = " + id;
            System.out.println(sql);
            ok = st.executeUpdate(sql) == 1;
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(WorkDB.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      return ok;
   }
   
  public boolean updateTable(String name, String[] names, Object[] values
          , Integer id) {
      boolean ok = false;
      if (name.startsWith("v")) {
         name = name.substring(1);
      }
      //Connection conn = LibraryFrame.conn;//getConnect();
      //Connection conn = getConnect();
      if (conn != null) {
         try {
            Statement st = conn.createStatement();
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(name).append(" SET ");
            for (int i = 0;  i < names.length; i++) {
               sql.append(names[i]).append("=");
               if (values[i] instanceof String) {
                  if (((String)values[i]).isEmpty()) {
                     values[i] = null;
                     sql.append(values[i]);
                  } else {
                     sql.append("'").append(values[i]).append("'");
                  }
               } else if (values[i] instanceof java.util.Date) {
                  sql.append("'").append(new java.sql.Date(((java.util.Date)values[i]).getTime())).append("'");
               } else {
                  sql.append(values[i]);
               }
               sql.append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(" WHERE id = ").append(id);
	    
            System.out.println(sql);
            ok = st.executeUpdate(sql.toString()) == 1;
         } catch (SQLException ex) {
            Logger.getLogger(WorkDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      }
      return ok;
   }
   
   public enum ModeEnum {
      INSERT, UPDATE, FIND;
   }
   private Connection conn = null;
}

