package org.essilab.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.essilab.module.announce.model.Announce;
import org.essilab.module.interest.model.Interest;

public class SearchAnnounceForm {
	private static final String INTERESTS   = "interests";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public List<Interest> SearchAnnounce( HttpServletRequest request ) {
    	String interests = getFieldValue( request, INTERESTS );
    	if(interests != null){
    		List<Interest> list = new ArrayList<Interest>();
    		String str[]=interests.split(",");
    		for(String s : str){
    			Interest in = new Interest(s);
    			list.add(in);
    		}
    		return list;
    	}
    	else{
    		System.out.println("SearchAnnounceForm.java");
    	}
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
