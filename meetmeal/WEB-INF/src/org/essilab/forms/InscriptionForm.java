package org.essilab.forms;
 
import java.util.HashMap;
import java.util.Map;
import org.essilab.module.user.model.User;
import javax.servlet.http.HttpServletRequest;
 
public final class InscriptionForm {
    private static final String FIELD_EMAIL  = "email";
    private static final String FIELD_PASS   = "password";
    private static final String FIELD_CONF   = "confirmation";
    private static final String LAST_NAME    = "last_name";
    private static final String FIRST_NAME   = "first_name";
    private String           	result;
    
    private Map<String, String> errors      = new HashMap<String, String>();
     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public User userInscription( HttpServletRequest request ) {
        String email = getFieldValue( request, FIELD_EMAIL );
        String password = getFieldValue( request, FIELD_PASS );
        String confirmation = getFieldValue( request, FIELD_CONF );
        String last_name = getFieldValue( request, LAST_NAME );
        String first_name = getFieldValue( request, FIRST_NAME );
        User User = new User();
     
        try {
            validEmail( email );
        } catch ( Exception e ) {
            setError( FIELD_EMAIL, e.getMessage() );
        }
        User.setEmail( email );
     
        try {
            validPassword( password, confirmation );
        } catch ( Exception e ) {
            setError( FIELD_PASS, e.getMessage() );
            setError( FIELD_CONF, null );
        }
        User.setPassword( password );
     
        try {
            validName( last_name );
        } catch ( Exception e ) {
            setError( LAST_NAME, e.getMessage() );
        }
        User.setLastname( last_name );
        
        try {
            validName( first_name );
        } catch ( Exception e ) {
            setError( FIRST_NAME, e.getMessage() );
        }
        User.setFirstname( first_name );        
     
        if ( errors.isEmpty() ) {
            result = "Succès de l'inscription.";
        } else {
            result = "Echec de l'inscription.";
        }
        
        return User;
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
     
    private void validPassword( String password, String confirmation ) throws Exception {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( password.length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }
     
    private void validName( String last_name ) throws Exception {

        if ( last_name == null || last_name.length() < 3 ) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
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