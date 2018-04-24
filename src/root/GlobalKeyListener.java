/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.Color;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;



public class GlobalKeyListener implements NativeKeyListener {
    
        static int toggleKey = NativeKeyEvent.VC_CAPS_LOCK;
        static GUI gui;

        
        static public void setGui(GUI g){
            gui = g;
        }
        
        public void setGuiColor(Color c){
        gui.setBackground(c);
        }
   
	public void nativeKeyPressed(NativeKeyEvent e) {
		
               //System.out.println(toggleKey);
                
		if (e.getKeyCode() == toggleKey) {
			ColorChecker.active = true;
                        setGuiColor(new Color(0.3f,1.0f,0.3f,0.5f));
		}
                
                gui.keyPressed(e);
                
        }

	public void nativeKeyReleased(NativeKeyEvent e) {
            if (e.getKeyCode() == toggleKey) {
		ColorChecker.active = false;
                setGuiColor(new Color(1.0f,1.0f,1.0f,0.5f));
            }
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
	}
}