package org.essilab.forms;
 
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
 
public final class ContactForm {
    private static final String FIELD_RESTONAME  = "resto_name";
    private static final String FIELD_EMAIL   = "email";
    private static final String FIELD_OBJECT   = "object";
    private static final String FIELD_MESSAGE    = "message";
    private String           	result;
    
    private Map<String, String> errors      = new HashMap<String, String>();
     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public void sendMail( HttpServletRequest request ) {
        String email = getFieldValue( request, FIELD_EMAIL );
        String resto_name = getFieldValue( request, FIELD_RESTONAME );
        String object = getFieldValue( request, FIELD_OBJECT );
        String message = getFieldValue( request, FIELD_MESSAGE );
        
     
        try {
            validEmail( email );
        } catch ( Exception e ) {
            setError( FIELD_EMAIL, e.getMessage() );
        }
     
        try {
            validName( resto_name );
        } catch ( Exception e ) {
            setError( FIELD_RESTONAME, e.getMessage() );
        }
    
     
        if ( errors.isEmpty() ) {
            result = "Message envoyé.";
        } else {
            result = "Echec de l'envoi.";
        }
        
        
    }
    
    private void validEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }
     
    private void validName( String name ) throws Exception {

        if ( name == null || name.length() < 1 ) {
            throw new Exception( "Veuilmez saisir le nom de votre restaurant." );
        }
    }
     
    /*
     * Add message to map error
     */
    private void setError( String field, String message ) {
        errors.put( field, message );
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