/**
 * 
 */
package com.hastybox.typeincludetaglib.path;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;

import com.hastybox.typeincludetaglib.model.ArticleImpl;

/**
 * @author psy
 *
 */
public class SimpleIncludeServiceTest {
	
	/**
	 * service to test
	 */
	private SimpleIncludeService includeService;
	
	/**
	 * base path
	 */
	private String basePath;
	
	/**
	 * object to analyze
	 */
	private Object object;
	
	/**
	 * template to render
	 */
	private String template;
	
	private PageContext pageContext;
	
	private ServletContext servletContext;
	
	private ServletRequest request;
	
	private RequestDispatcher requestDispatcher;
	
	private String packagePath = "com.hastybox.typeincludetaglib.model/";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		includeService = new SimpleIncludeService();
		
		basePath = "/some/path/";
		
		includeService.setBasePath(basePath);
		
		pageContext = mock(PageContext.class);
		servletContext = mock(ServletContext.class);
		request = mock(ServletRequest.class);
		requestDispatcher = mock(RequestDispatcher.class);
		
		when(pageContext.getRequest()).thenReturn(request);
		when(pageContext.getServletContext()).thenReturn(servletContext);
		
		object = null;
		template = null;
	}

	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetInclude() throws Exception {
		object = new ArticleImpl();
		template = null;
		
		String path = basePath + packagePath + "ArticleImpl.jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeTemplate() throws Exception {
		object = new ArticleImpl();
		template = "teaser";
		
		String path = basePath + packagePath + "ArticleImpl." + template + ".jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeInterfaceTemplate() throws Exception {
		object = new ArticleImpl();
		template = "teaser";
		
		String path = basePath + packagePath + "Article." + template + ".jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeSuperClassTemplate() throws Exception {
		object = new ArticleImpl();
		template = "teaser";
		
		String path = basePath + packagePath + "ArticleBase." + template + ".jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeSuperClassInterfaceTemplate() throws Exception {
		object = new ArticleImpl();
		template = "teaser";
		
		String path = basePath + packagePath + "Teasable." + template + ".jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeObjectTemplate() throws Exception {
		object = new ArticleImpl();
		template = "teaser";
		
		String path = basePath + "java.lang/Object." + template + ".jsp";
		when(servletContext.getResourceAsStream(path)).thenReturn(new ByteArrayInputStream(new byte[]{}));
		when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
		
		RequestDispatcher rd = includeService.getInclude(object, template, pageContext);
		
		assertNotNull(rd);
		assertTrue(includeService.getPathStore().size() == 1);
	}
	
	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#getInclude(java.lang.Object, java.lang.String, javax.servlet.jsp.PageContext)}.
	 */
	@Test
	public void testGetIncludeNotFound() throws Exception {
		object = new ArticleImpl();
		template = null;
		try {
			includeService.getInclude(object, template, pageContext);
			fail();
		} catch (Exception e) {
			// pass
		}
		
		assertTrue(includeService.getPathStore().isEmpty());
	}

	/**
	 * Test method for {@link com.hastybox.typeincludetaglib.path.IncludeServiceFactory#clearCache()}.
	 */
	@Test
	public void testClearCache() {
		Map<String, String> pathStore = includeService.getPathStore();
		
		pathStore.put("something", "somethingElse");
		
		assertFalse(pathStore.isEmpty());
		
		includeService.clearCache();
		
		assertTrue(pathStore.isEmpty());
	}

}
