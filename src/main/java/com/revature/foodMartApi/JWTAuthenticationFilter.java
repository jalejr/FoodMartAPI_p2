/**
 * 
 */
package com.revature.foodMartApi;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foodMartApi.exceptions.AuthenticationException;
import com.revature.foodMartApi.models.User;

/**
 * @author Awaab
 * @Date 03/08/2022
 * @Description This class extends UsernamePasswordAuthenticationFilter which is
 *              the default class for password authentication in Spring
 *              Security. We extend it to define our custom authentication logic
 * 
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final String SECRET = "p1 secert";
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		// This method sets the default login URL to the provided parameter.
		// If you remove this line, Spring Security creates the “/login” end point by
		// default.It defines the login end point for us, which is why we will not
		// define a login end point in our controller explicitly.
		setFilterProcessesUrl("/api/services/controller/user/login");
	}

	/**
	 * runs when the user tries to log in to our application. It reads the
	 * credentials, creates a user POJO from them, and then checks the credentials
	 * to authenticate.
	 * @return an Authentication object that contains the authorities we passed while attempting.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

			// We pass the user name, password, and an empty list. The empty list represents
			// the authorities (roles)
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * If the authentication is successful, the successfulAuthentication method
	 * runs. The parameters of this method are passed by Spring Security behind the
	 * scenes.
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException {
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
						.sign(Algorithm.HMAC512(SECRET));

		String body = ((User) auth.getPrincipal()).getUsername() + " " + token;

		res.getWriter().write(body);
		res.getWriter().flush();
	}

	public static String getSecret() {
		return SECRET;
	}
}
