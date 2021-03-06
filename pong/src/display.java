package src;

import java.awt.*;
import javax.swing.JFrame;

public class display extends Canvas{
    private static final float SerialNumber = 1.05f;
    public display(int width, int height, String title, master game){
        JFrame frame = new JFrame(title + " v" + SerialNumber);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.AddListener(frame);
        game.start();
    }
}



