/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam Lamine
 */
public class ColorChecker extends TimerTask {
    
    static int treshold = 85;
    int crosshairOffset = 1;
    
    long startTime = System.nanoTime();
    
    static boolean active = false;
    
    double redWeightFactor = 1.3;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
    int height = (int)screenSize.getHeight();
    
    Color newColor = this.getColor(width/2, height/2);
    Color oldColor = this.getColor(width/2, height/2);
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    public Color getColor(int x, int y){
        

        
        try {
            
            Robot robot = new Robot();
            
            Color color = robot.getPixelColor(x, y);
            return color;

        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        } 
           
           
    }
    
    public void checkChange() throws AWTException{
        
        this.newColor = this.getColor(width/2 - crosshairOffset, height/2 - crosshairOffset);

        if( (newColor.getRed() * redWeightFactor + newColor.getGreen() + newColor.getBlue()) + treshold < (oldColor.getRed() * redWeightFactor + oldColor.getGreen() + oldColor.getBlue())    ||
            (newColor.getRed() * redWeightFactor + newColor.getGreen() + newColor.getBlue()) - treshold > (oldColor.getRed() * redWeightFactor + oldColor.getGreen() + oldColor.getBlue()))
        {
        shoot();
        }
        
        this.oldColor = this.getColor(width/2 - crosshairOffset, height/2 - crosshairOffset);
        
    }
    
    public void shoot() throws AWTException{
        
        if(startTime < System.nanoTime() - 2140000000 - 40000000 && active){
            this.startTime = System.nanoTime();
            Robot robot = new Robot();
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
        
        

    }
    

    @Override
    public void run() {
        
        try {
            checkChange();
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (AWTException ex) {
            
            Logger.getLogger(ColorChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
