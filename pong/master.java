package pong;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class master extends Canvas implements Runnable{
    
    rect player = new rect(5,5,7,65);
    rect bot = new rect(770,5,7,65);
    ball bola = new ball(400,300,10,10);
    status s = new status();
    Font ft = new Font("CozetteVector", Font.PLAIN, 36);
    keys k = new keys();
    
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;


    
    public master(){
        new display(WIDTH, HEIGHT, "Pong", this);
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
                handleInput();
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
        
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        

        player.draw(g, Color.BLUE);
        bot.draw(g, Color.RED);
        bola.draw(g, Color.MAGENTA);
        g.setColor(Color.BLACK);

        g.setFont(ft);

        g.drawString(s.playerP + " | " + s.enemyP, 325, 50);
        
        g.dispose();
        b.show();
    }
    
    
    private void update(){
        bola.update(player, bot, s);
        if(bola.y <= 800 - 110 || bola.y < 3){
            if(bola.y > bot.y + (bot.h/2)){
                bot.y += 2;
            }
            if(bola.y < bot.y + (bot.h/2)){
                bot.y -= 2;
            }
        }
        if(s.enemyP == 6 || s.playerP == 6){
            reset();
        }
    }
    
    private void handleInput(){
        if(k.CheckW()){
            if(player.y > 3){
                 player.y -= 3;
            }
             
        }
        else if(k.CheckS()){
             if(player.y < 800 - 110){
                 player.y += 3;
             }
        }
        
    }

    public void reset(){
        soundhndl.playSound("death.wav");
        player = new rect(5,5,7,65);
        bot = new rect(770,5,7,65);
        s = new status();
        bola.start();
        run();
    }
    
    public void AddListener(JFrame j){
        j.addKeyListener(k);
    }
    
    public static void main(String[] args){
        new master();
    }
}
