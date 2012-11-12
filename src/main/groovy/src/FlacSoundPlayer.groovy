/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.LineUnavailableException

import org.kc7bfi.jflac.sound.spi.FlacAudioFileReader

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

            FlacAudioFileReader audioReader = new FlacAudioFileReader()

            InputStream bufferedInputStream = new BufferedInputStream(asInputStream(fileName))
            AudioInputStream audioStream = audioReader.getAudioInputStream(bufferedInputStream)

            playAudioStream(audioStream);

            bufferedInputStream.close();

            bufferedInputStream.closeQuietly();
        }
    }
}
