/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.EventListener;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;

/**
 *
 * @author hamster
 */
public class SocketServer {
   
   public static int DEFAULT_PORT = 6666;
   private int port = DEFAULT_PORT;
   String line = null;
   public boolean isConnect = true;
   ServerSocket ss = null;
   protected EventListenerList listenerList = new EventListenerList();
   
   public SocketServer(int port) {
      this.port = port;
   }

   public SocketServer() {
     
   }

   public int getPort() {
      return port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public String getLine() {
      return line;
   }
   
   
   public void connect(){
      SubClass sb = new SubClass();
      Thread t = new Thread(sb);
      t.start();
   }
   
   public void addLineReadListener(LineReadListener l){
      listenerList.add(LineReadListener.class, l); 
   }
   public void fireLineReadEvent(LineReadEvent e){
      EventListener [] listeners = listenerList.getListeners(LineReadListener.class);
      
      for (int i=0; i < listeners.length; i++){
         LineReadListener listener = (LineReadListener) listeners[i];
         listener.showLine(e);
      }
   }
   

   public class SubClass implements Runnable{

      @Override
      public void run() {
         try {
         ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Waiting for a client...");

         while(true) {
         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         if (isConnect){
         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

           line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
           
           LineReadEvent e = new LineReadEvent(line);
           fireLineReadEvent(e);
           
           System.out.println("The dumb client just sent me this line : " + line);
           out.writeUTF("Ok"); // отсылаем клиенту 
           out.flush(); // заставляем поток закончить передачу данных.
         //}
          } else {
             ss.close();
       }
         }
      } catch(SocketException e){
         JOptionPane.showMessageDialog(null, e.getMessage(), "Something wrong with server!", JOptionPane.OK_OPTION);
         //Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, "Server was stopped!!", e.getMessage());
        // e.printStackTrace();
      }catch(IOException s){
         s.printStackTrace();
      } finally {
        Thread.currentThread().interrupt();
      }

         /*finally{
            out.flush();
         }*/ 
      }
   }
}
