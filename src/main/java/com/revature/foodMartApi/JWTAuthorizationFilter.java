/**
 * 
 */
package com.revature.foodMartApi;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author Awaab
 * @Date 03-08-2022
 * @Description This filter will check the existence and validity of the access
 *              token on the Authorization header. We will specify which end
 *              points will be subject to this filter in our configuration
 *              class.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(req, res);
			return;
		}
		// If the header is present, the getAuthentication method is invoked.
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		//This new token is then saved to SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	// Reads the JWT from the Authorization header, and then uses JWT to validate
	// the token

	/**
	 * verifies the JWT, and if the token is valid	 * 
	 * @param request Reads the JWT from the Authorization header and then uses JWT to validate the token
	 * @return an access token which Spring will use internally.
	 */

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.getSecret()) )
					.build()
					.verify(token.replace("Bearer ", ""))
					.getSubject();

			if (user != null) {
				// new array list means authorities
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			return null;
		}

		return null;
	}

}
