/**
 * 
 */
package com.hastybox.typeincludetaglib.path;

import javax.servlet.RequestDispatcher;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * IncludeService interface.
 * 
 * @author psy
 * 
 */
public interface IncludeService {

	/**
	 * retrieves include JSP for given {@code object} and {@code template}
	 * 
	 * @param o
	 *            object that defines the template through its type
	 * @param template
	 *            template to render
	 * @param pageContext
	 *            current page context
	 * @return request dispatcher holding JSP for include in response
	 * @throws JspException
	 *             if the JSP was not found
	 */
	RequestDispatcher getInclude(Object o, String template,
			PageContext pageContext) throws JspException;

}
