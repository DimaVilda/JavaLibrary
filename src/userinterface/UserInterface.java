package userinterface;

import java.awt.Component;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author hamster
 */
public class UserInterface {
   public static void saveSizeLocation(Component dialog, String name) {
      saveSizeLocation(dialog, name, null);
   }
   
   public static void saveSizeLocation(Component dialog, String name, JTable table) {
      if (dialog == null) {
         return;
      }
      Properties props = new Properties();
      OutputStream output = null;
      props.setProperty("x", String.valueOf(dialog.getLocationOnScreen().x));
      props.setProperty("y", String.valueOf(dialog.getLocationOnScreen().y));
      props.setProperty("width", String.valueOf(dialog.getSize().width));
      props.setProperty("height", String.valueOf(dialog.getSize().height));
      if (table != null) {
         for (int i = 0; i < table.getColumnCount(); i++) {
            props.setProperty("columnWidth"+i, String.valueOf(table.getColumnModel().getColumn(i).getWidth()));
         }   
      }
      try {
         output = new FileOutputStream(name+".properties");
         props.store(output, null);
         output.close();
         
      } catch (FileNotFoundException ex) {
      } catch (IOException ex) {
         Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   public static void restoreSizeLocation(Component dialog, String name) {
      restoreSizeLocation(dialog, name, null);
   }
   
   public static void restoreSizeLocation(Component dialog, String name, JTable table) {
      if (dialog == null) {
         return;
      }
      Properties props = new Properties();
      InputStream input = null;
      try {
         input = new FileInputStream(name+".properties");
         props.load(input);
         dialog.setLocation(Integer.parseInt(props.getProperty("x")),
                 Integer.parseInt(props.getProperty("y")));
         dialog.setSize(Integer.parseInt(props.getProperty("width")),
                 Integer.parseInt(props.getProperty("height")));
         if (table != null) {
            for (int i = 0; i < table.getColumnCount(); i++) {
               table.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(
                       props.getProperty("columnWidth"+i)));
            }   
         }
         input.close();
         
      } catch (NumberFormatException ex) {
      } catch (FileNotFoundException ex) {
         //Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
         dialog.setSize(600, 300);
      } catch (IOException ex) {
         Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
