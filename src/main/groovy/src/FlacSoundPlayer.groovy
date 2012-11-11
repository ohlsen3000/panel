/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import org.kc7bfi.jflac.apps.Player;
import src.SoundPlayer;

/**
 *
 * @author tangente
 */
public class FlacSoundPlayer extends AbstractSoundPlayer implements SoundPlayer {
        
    protected String getSuffix(){
        return "flac";
    }
    
    public void play(String fileName) throws IOException,
    LineUnavailableException{
                       
        Thread.start {
            File file = asFile(fileName);
            new Player().decode(file.absolutePath);
        }
        
    }
}
