package src;

/**
 *
 * @author fuschl
 */
public interface SoundPlayer {
    boolean canPlay(String fileName);
    
    void play(String filenMame) throws Exception;
}
