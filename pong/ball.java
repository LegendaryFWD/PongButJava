package pong;

import java.awt.*;
import java.util.Random;

public class ball {
    public int x;
    public int y;
    public int h;
    public int w;
    
    private int xDir = 1;
    private float yDir = 2;

    public ball(int x, int y, int h, int w){
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;

        Random rd = new Random();
        xDir = rd.nextBoolean() ? xDir : -xDir;
        

    }

    public void draw(Graphics g, Color c){
        g.setColor(c);
        g.fillOval(x, y, h, w);
    }

    public void update(){
        if(x > 775 || x < 0){
            xDir = -xDir;
        }
        if(y > 750 || y < 0){
            yDir = -yDir;
        }
        x += xDir;
        y += yDir;
    }

}
