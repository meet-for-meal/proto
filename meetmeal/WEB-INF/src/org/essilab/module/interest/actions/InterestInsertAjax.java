package org.essilab.module.interest.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.essilab.module.interest.InterestService;
import org.essilab.module.interest.model.Interest;
import org.essilab.servlet.IAction;

public class InterestInsertAjax implements IAction{

	public final static String FIELD_TAG = "tag";
	
	InterestService service = InterestService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean ok = false;
			Interest i = readPost(request);
			response.setContentType(HEADER_TYPE_JSON);
			if (i != null)
				ok = service.interestInsert(i);
			response.getWriter().println(ok ? RESPONSE_OK : RESPONSE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("render", false);
	}
	
	
    public Interest readPost(HttpServletRequest request) {
        String tag = getFieldValue( request, FIELD_TAG );
        Interest i = new Interest(tag);
     
        return i;
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
