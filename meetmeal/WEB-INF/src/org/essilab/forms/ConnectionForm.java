package org.essilab.forms;

import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import org.essilab.module.user.model.User;
 
public final class ConnectionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";
 
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();
 
    public String getresult() {
        return result;
    }
 
    public Map<String, String> getErrors() {
        return errors;
    }
 
    public User connectUser( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String password = getValeurChamp( request, CHAMP_PASS );
 
        User user = new User();
 
        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setEmail( email );
 
        /* Validation du champ mot de passe. */
        try {
            validationPassword( password );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        user.setPassword( password );
 
        /* Initialisation du résultat global de la validation. */
        if ( errors.isEmpty() ) {
            result = "Succès de la connexion.";
        } else {
            result = "Échec de la connexion.";
        }
 
        return user;
    }
 
    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email == null || !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }
 
    /**
     * Valide le mot de passe saisi.
     */
    private void validationPassword( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }
 
    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setErreur( String champ, String message ) {
        errors.put( champ, message );
    }
 
    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
