package ar.edu.unq.tac.market.auditory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityHelper {

	public static final String UNKNOWN_USER = "unknown";
	
	/**
	 * Returns the current Authentication object
	 * 
	 * @return the Spring security Authentication obejct
	 */
	private static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}
		Authentication authentication = context.getAuthentication();
		return authentication;
	}
	
	
	
	 
	public static String getLoggedUsername() {

		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return UNKNOWN_USER;
		}
		return authentication.getName();

	}
	
}
