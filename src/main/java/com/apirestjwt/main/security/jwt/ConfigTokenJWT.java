package com.apirestjwt.main.security.jwt;

public class ConfigTokenJWT {
	private static final String secret = "apicontato";
	private static final String prefix = "Bearer";
	private static final String header = "Authorization";
	private static final long expirationTime = 864000000;
	public static String getSecret() {
		return secret;
	}
	public static String getPrefix() {
		return prefix;
	}
	public static String getHeader() {
		return header;
	}	
	public static long getExpirationTime() {
		return expirationTime;
	}
}
