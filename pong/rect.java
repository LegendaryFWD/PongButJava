package pong;

import java.awt.Graphics;
import java.awt.Color;

public class rect {
    public int x;
    public int y;
    public int h;
    public int w;

    public rect(int x, int y, int h, int w){
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, Color c){
        g.setColor(c);
        g.drawRect(x, y, h, w);
    }

}
