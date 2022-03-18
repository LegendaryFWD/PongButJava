package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keys implements KeyListener {

    private int currentChar;
    
    public boolean CheckW(){
        if(currentChar == KeyEvent.VK_W){
            return true;
        }
        return false;
    }

    public boolean CheckS(){
        if(currentChar == KeyEvent.VK_S){
            return true;
        }
        return false;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }


    @Override
    public void keyPressed(KeyEvent e) {
        currentChar = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(currentChar == KeyEvent.VK_W){
            currentChar = 0;
        }
        else if(currentChar == KeyEvent.VK_S){
            currentChar = 0;
        }
        
    }
    
}
