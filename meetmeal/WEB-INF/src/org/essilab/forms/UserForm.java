package org.essilab.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.essilab.module.category.CategoryService;
import org.essilab.module.category.model.Category;
import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.module.user.model.User;

public class UserForm {
	private static final String FIRSTNAME   = "firstname";
	private static final String LASTNAME   = "lastname";
	private static final String GENDER   = "gender";
	private static final String AGE   = "age";
	private static final String INTEREST   = "interest";
	private static final String CATEGORY   = "category";

    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public User GetUserUpdate( HttpServletRequest request ) {
    	
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");
        InterestService interestservice = InterestService.getInstance();
        CategoryService categoryservice = CategoryService.getInstance();
        
    	
    	String firstname = getValeurChamp( request, FIRSTNAME );
        String lastname = getValeurChamp( request, LASTNAME );
        String gender = getValeurChamp( request, GENDER );
        String age = getValeurChamp( request, AGE );
        String[] si = request.getParameterValues(INTEREST);
        String[] sc = request.getParameterValues(CATEGORY);
        
        
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setGender(Integer.parseInt(gender));
        user.setAge(Integer.parseInt(age));
        interestservice.interestDeleteByUser(user.getId());
        categoryservice.catgoryDeleteByUser(user.getId());
        for(int i = 0; i < si.length; i++)
        {
        	interestservice.interestInsertByUser(user.getId(), Integer.parseInt(si[i]));
        }
        for(int i = 0; i < sc.length; i++)
        {
        	categoryservice.categoryInsertByUser(user.getId(), Integer.parseInt(sc[i]));
        }
        
        return user;
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
