package com.modumind.spacex.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.modumind.spacex.service.model.Launch;

/**
 * Client for the r/SpaceX Launch Service. Specifications for this service can be found at:
 * 
 * https://documenter.getpostman.com/view/2025350/RWaEzAiG?version=latest
 * 	
 * @author Patrick Paulin
 */
@Path("/launches")
public interface LaunchService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	List<Launch> getLaunches();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	CompletableFuture<List<Launch>> getLaunchesAsync();
}