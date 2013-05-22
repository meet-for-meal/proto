package org.essilab.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;

public class CreateAnnounceForm {
	private static final String DISPO   = "dispo";
	private static final String DESCRIPTION   = "description";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public Announce CreateAnnounce( HttpServletRequest request ) {
    	String interests = getFieldValue( request, DISPO );

    	return null;
    }
	
    

    
    /*
     * Add message to map error
     */
    private void setError( String champ, String message ) {
        errors.put( champ, message );
    }
    
    
    /*
     * Return field value or null
     */
    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String valeur = request.getParameter( fieldName );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
