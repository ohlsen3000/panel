/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package src
import org.springframework.core.io.ClassPathResource;
/**
 *
 * @author tangente
 */
abstract class AbstractSoundPlayer {
	
    abstract  protected String getSuffix();
    
    private String completeFileName(String filename){
        return '/src/'.plus(filename).plus('.').plus(getSuffix());
    }
    
    protected asFile(String filename){
        String complete = completeFileName(filename);
        ClassPathResource resource = new ClassPathResource(complete);
        resource.file
    }
    public boolean canPlay(String filename) {
        String complete = completeFileName(filename);
        ClassPathResource resource = new ClassPathResource(complete);
        boolean exists = resource.exists();
        
        // TODO cache it
        return exists
    }
}

