/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

import org.springframework.core.io.ClassPathResource
/**
 *
 * @author tangente
 */
abstract class AbstractSoundPlayer {

    abstract  protected String getSuffix();

    private String completeFileName(String filename){
        return '/src/'.plus(filename).plus('.').plus(getSuffix());
    }

    protected asInputStream(String filename){
        String complete = completeFileName(filename);
        ClassPathResource resource = new ClassPathResource(complete);
        resource.inputStream;
    }
    public boolean canPlay(String filename) {
        String complete = completeFileName(filename);
        ClassPathResource resource = new ClassPathResource(complete);
        boolean exists = resource.exists();

        // TODO cache it
        return exists
    }

    protected void playAudioStream(AudioInputStream audioStream){

        // Open audio clip and load samples from the audio input stream.
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }
}



