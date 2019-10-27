package com.apirestjwt.main.request;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserHttpRequestServelt extends HttpServletRequestWrapper {
	
	/* Custom Headers */
	private Map<String, String> headers;

	
	public UserHttpRequestServelt(HttpServletRequest request) {
		super(request);
		this.headers = new HashMap<String, String>();
	}
	
	/**
	 * Add new Head
	 * @param key
	 * @param value
	 */
	public void putHead(String key,String value) {
		this.headers.put(key, value);
	}
	
	/**
	 * Get one Head
	 */
	@Override
	public String getHeader(String key) {
		String head = this.headers.get(key);
		if(head != null)
			return head;

        return ((HttpServletRequest) getRequest()).getHeader(key);
	}
	
	@Override
	public Enumeration<String> getHeaderNames() {
	    // create a set of the custom header names
        Set<String> set = new HashSet<String>(this.headers.keySet());
        
        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }
 
        // create an enumeration from the set and return
        return Collections.enumeration(set);
	}
	

}
