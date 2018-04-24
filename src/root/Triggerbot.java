/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


/**
 *
 * @author Adam Lamine
 */
public class Triggerbot {
    
    
    public static void main(String[] args) {            
            Timer timer = new Timer();
            timer.schedule(new ColorChecker(), 0, 1);
            
            GUI gui = new GUI();
            gui.init();
            
            
    
            //jnativehook (keylistener)
            try {
                                GlobalScreen.registerNativeHook();
                        }
                        catch (NativeHookException ex) {
                                System.err.println("There was a problem registering the native hook.");
                                System.err.println(ex.getMessage());

                                System.exit(1);
                        }

                        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
                            // Clear previous logging configurations.
             LogManager.getLogManager().reset();

             // Get the logger for "org.jnativehook" and set the level to off.
             Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
             logger.setLevel(Level.OFF); 

            
    }
    
    
    
    
    
    
    
}
    
    
    
    
    
    
    

