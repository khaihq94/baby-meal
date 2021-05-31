package vn.hqkhai.babymeal.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import vn.hqkhai.babymeal.service.JwtUserDetailsService;
import vn.hqkhai.babymeal.utils.JwtTokenUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final String BEARER = "Bearer ";
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;

//	public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtils jwtTokenUtils) {
//		this.jwtTokenUtils = jwtTokenUtils;
//		this.jwtUserDetailsService = jwtUserDetailsService;
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (StringUtils.isNotBlank(requestTokenHeader) && requestTokenHeader.startsWith(BEARER)) {
			jwtToken = StringUtils.substringAfter(requestTokenHeader, BEARER);
			try {
				username = jwtTokenUtils.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			if (StringUtils.isBlank(requestTokenHeader)) {
				logger.warn("JWT Token is empty");
			} else {
				logger.warn("JWT Token does not begin with Bearer String");
			}
		}

		// Once we get the token validate it.
		if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtils.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
