/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import org.kc7bfi.jflac.apps.Player;

/**
 *
 * @author tangente
 */
public class FlacSoundPlayer {
    
    Player player = new Player();
    
    public void play(String file) throws IOException,
                   LineUnavailableException{
        
        this.player.decode(file);
        
    }
}
