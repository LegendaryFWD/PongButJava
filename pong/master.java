package pong;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class master extends Canvas implements Runnable{
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;

    
    public master(){
        new display(WIDTH, HEIGHT, "Pong v1.01", this);
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
        run();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        long LastUpd = System.nanoTime();
        final double ns = 1000000000.0 / 60;
        double delta = 0;  
        while(running){
            long ag = System.nanoTime();
            delta += (ag - LastUpd) / ns;
            LastUpd = ag;
            while(delta  >= 1){
                update();
                delta--;
                render();
            }
        }
        stop();
    }

    private void render(){
        BufferStrategy b = this.getBufferStrategy();
        if(b == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = b.getDrawGraphics();
        
        g.setColor(Color.RED);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        g.dispose();
        b.show();
    }
    
    private void update(){

    }
    
    public static void main(String[] args){
        new master();
    }
}
