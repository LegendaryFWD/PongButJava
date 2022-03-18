package src;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class soundhndl {
     private static final String srcPath = "pong/res/";
     public static void playSound(String path){
        try{
            AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(srcPath + path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIS);
            clip.start();
        } 
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        }
}
