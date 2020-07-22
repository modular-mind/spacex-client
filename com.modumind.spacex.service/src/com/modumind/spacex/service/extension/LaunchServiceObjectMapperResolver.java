package com.modumind.spacex.service.extension;

import javax.ws.rs.ext.ContextResolver;

import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Component(service = ContextResolver.class)
public class LaunchServiceObjectMapperResolver implements ContextResolver<ObjectMapper> {

	private ObjectMapper objectMapper;
	
	public LaunchServiceObjectMapperResolver() {
	    objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	    
	    /* Add custom deserializers, etc. if needed */
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return objectMapper;
	}
}
