package pong;

import java.awt.*;
import java.util.Random;

public class ball {
    public int x;
    public int y;
    public int h;
    public int w;
    public float spdFactor = 1;
    
    private int xDir = -1;
    private float yDir = 2;
    
    Random rd = new Random();
    public ball(int x, int y, int h, int w){
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;

        
        xDir = rd.nextBoolean() ? xDir : -xDir;
        

    }

    public void draw(Graphics g, Color c){
        g.setColor(c);
        g.fillOval(x, y, h, w);
    }

    public void start(){
        long tm = System.currentTimeMillis();
        long end = tm + 3000;
        while(System.currentTimeMillis() < end){
            xDir = 0;
            yDir = 0;
        }
        
        xDir = -1;
        xDir = rd.nextBoolean() ? xDir : -xDir;
        yDir = 2;
    }
    
    public void update(rect p, rect b, status s){
        if(y >= p.y && y <= p.y + 65 && x <= p.x + 5){
            xDir = -xDir;
        }
        if(y >= b.y && y <= b.y + 65 && x >= b.x - 7){
            xDir = -xDir;
        }
        if(y > 750 || y < 0){
            yDir = -yDir;
        }
        
        if(x < -5){
            s.enemyP += 1;
            x = 400;
            y = 350;
            soundhndl.playSound("hit.wav");
        }
        if(x > 800){
            s.playerP += 1;
            x = 400;
            y = 350;
        }
        
        spdFactor += 1/30;
        x += xDir * spdFactor;
        y += yDir * spdFactor;
    }

}
