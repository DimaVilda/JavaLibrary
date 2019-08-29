/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author hamster
 */
public class SocketClient {
   private int serverPort = SocketServer.DEFAULT_PORT;
   private String msg = null;
   
   public SocketClient() {
   }
   
   /*public SocketClient(int port) {
      this.serverPort = port;
   }*/
    
   public void submit(String address){
      if (serverPort > 1025 && serverPort < 65535 ){
         try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

                System.out.println("Sending this line to the server...");
                out.writeUTF(msg); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.
                
                msg = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("Сервер отослал строку");
        } catch (Exception x) {
            x.printStackTrace();
        }
      }
   }
   
   public String setMsg(String txt){
      return this.msg = txt;
   }
   public int getServerPort() {
      return serverPort;
   }
   
}
