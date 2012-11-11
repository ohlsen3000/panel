/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tangente
 */
public class SoundPlayerService {
    private List<SoundPlayer> availablePlayers = new ArrayList<>();
    
    
    private void init(){
        this.availablePlayers.add(new WavSoundPlayer());
        this.availablePlayers.add(new FlacSoundPlayer());
    }
    
    public SoundPlayerService(){
        init();
    }
    
    public void playSound(String soundName) throws Exception{
        for (SoundPlayer player : availablePlayers){
            if (player.canPlay(soundName)){
                player.play(soundName);
                break;
            }
        }
    }
}
