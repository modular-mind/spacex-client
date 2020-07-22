package com.modumind.spacex.service.extension;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;

@Component(service = ClientRequestFilter.class)
public class LaunchServiceClientRequestFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext context) throws IOException {
		MultivaluedMap<String, Object> headers = context.getHeaders();
		Map<String, Cookie> cookies = context.getCookies();
		
		/* Manipulate headers or cookies before request is made */
	}
}
