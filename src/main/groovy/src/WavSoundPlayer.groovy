/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

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
        InputStream inputStream = asInputStream(fileName);

        // Open an audio input stream.
        InputStream bufferedInputStream = new BufferedInputStream(inputStream)
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedInputStream);


        playAudioStream(audioStream);

        bufferedInputStream.close();

    }
    
    protected void playAudioStream(AudioInputStream audioStream){

        // Open audio clip and load samples from the audio input stream.
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

    }
}
