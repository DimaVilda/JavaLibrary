/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.util.EventListener;

/**
 *
 * @author hamster
 */
public interface LineReadListener extends EventListener {
   
   public void showLine(LineReadEvent e);
}
