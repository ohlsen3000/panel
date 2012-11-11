/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import src.SoundPlayer;

/**
 *
 * @author tangente
 */
public class WavSoundPlayer extends AbstractSoundPlayer implements SoundPlayer{

    private static final String SUFFIX = "wav";
	
    protected String getSuffix(){
        return SUFFIX;
    }

    @Override
    public void play(String fileName) throws Exception {
        File file = asFile(fileName);	
        
        // Open an audio input stream.
        InputStream bufferedInputStream = file.newInputStream();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);


        // Open audio clip and load samples from the audio input stream.
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

        bufferedInputStream.close();

    }
    
}
