/**
 * 
 */
package com.hastybox.typeincludetaglib.path;

/**
 * Creates request dispatcher for includes and stores paths for faster access.
 * 
 * @author psy
 * 
 */
public class IncludeServiceFactory {

	/**
	 * private constructor
	 */
	private IncludeServiceFactory() {
		// no instances
	}

	/**
	 * base path for type templates to include
	 */
	public static final String BASEPATH = "/WEB-INF/typeTemplates/";

	/**
	 * holding singleton instance of IncludeService
	 */
	private static IncludeService INCLUDE_SERVICE;

	/**
	 * Inject custom IncludeService implementation for usage in include tag.
	 * 
	 * @param includeService
	 * @return given includeService for convenience
	 */
	public static IncludeService setIncludeService(IncludeService includeService) {
		INCLUDE_SERVICE = includeService;

		return INCLUDE_SERVICE;
	}

	/**
	 * Returns singleton instance stored in factory. If no instance was injected
	 * through setter method, the standard implementation with default
	 * configuration is used.
	 * 
	 * @return
	 */
	public static IncludeService getIncludeService() {
		if (INCLUDE_SERVICE == null) {
			INCLUDE_SERVICE = new SimpleIncludeService(BASEPATH);
		}

		return INCLUDE_SERVICE;
	}

}
