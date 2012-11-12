/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.LineUnavailableException

import org.kc7bfi.jflac.sound.spi.FlacAudioFileReader
import org.springframework.core.io.ClassPathResource
import org.kc7bfi.jflac.apps.Player
import org.kc7bfi.jflac.FLACDecoder

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
            FLACDecoder decoder = new FLACDecoder(asInputStream(fileName));
            decoder.addPCMProcessor(new Player());
            try {
                decoder.decode();
            } catch (EOFException e) {
                // skip
            }
        }
    }
}
