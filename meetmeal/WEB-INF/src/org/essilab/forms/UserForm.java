package org.essilab.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.essilab.module.category.model.Category;
import org.essilab.module.interest.model.Interest;

public class UserForm {
	private static final String INTERESTS   = "interests";
	private static final String CATEGORIES   = "categories";
    private String              result;
    private Map<String, String> errors      = new HashMap<String, String>();

     
    public String getResult() {
        return result;
    }
     
    public Map<String, String> getErrors() {
        return errors;
    }
    
    
    public List<Interest> SearchInterest( HttpServletRequest request ) {
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
    		System.out.println("UserForm.java");
    	}
    	return null;
    }
	
    public List<Category> SearchCategory( HttpServletRequest request ) {
    	String categories = getFieldValue( request, CATEGORIES );
    	if(categories != null){
    		List<Category> list = new ArrayList<Category>();
    		String str[]=categories.split(",");
    		for(String s : str){
    			Category in = new Category(s);
    			list.add(in);
    		}
    		return list;
    	}
    	else{
    		System.out.println("UserForm.java");
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
