package org.essilab.forms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.essilab.module.announce.AnnounceService;
import org.essilab.module.announce.model.Announce;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class CreateAnnounceForm {
	private static final String DISPO_DATE   = "dispo-date";
	private static final String DISPO_HOUR   = "dispo-hour";
	private static final String DESCRIPTION  = "description";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public Announce CreateAnnounce( HttpServletRequest request, User user) {
    	String dispo_date = getFieldValue( request, DISPO_DATE );
    	String dispo_hour = getFieldValue( request, DISPO_HOUR );
    	String description = getFieldValue( request, DESCRIPTION);
    	AnnounceService announceService = AnnounceService.getInstance();
    	String [] split = dispo_date.split("-");
    	String [] split_hour = dispo_hour.split(":");
    	Calendar calendar = Calendar.getInstance();
    	Calendar current = Calendar.getInstance();
    	calendar.set(Integer.parseInt(split[2])+2000,Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split_hour[0]),Integer.parseInt(split_hour[1]));
    	Date mydispo_date = calendar.getTime();
    	Date created_date = new Date();
    	
    	Announce announce = new Announce(0,
				created_date, 
				mydispo_date,
				true,
				user.getLastLat(),
				user.getLastLong(),
				description,
				user);
    	announceService.announceInsert(announce);
    	return announce;
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
