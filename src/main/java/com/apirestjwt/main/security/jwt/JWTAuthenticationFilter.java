package com.apirestjwt.main.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apirestjwt.main.request.UserHttpRequestServelt;
import com.apirestjwt.main.security.TokenAuthenticationService;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil tokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{		
		 Authentication authentication = TokenAuthenticationService.getAuthentication( request);
		 	UserHttpRequestServelt requestUser =  new UserHttpRequestServelt(request);
		 //	requestUser.putHead("user", this.getToken(request));
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        filterChain.doFilter(requestUser, response);
		
	}
	
	private String getToken(HttpServletRequest req) {
		String token = req.getHeader(ConfigTokenJWT.getHeader()).replaceAll(ConfigTokenJWT.getPrefix(),"").replaceAll(" ","");
		System.out.println("Token ----- : "+token);
		return this.tokenUtil.getUsernameFromToken(token);		
	}

}