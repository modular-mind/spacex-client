package com.modumind.spacex.service;

/**
 * Facade providing access to r/SpaceX REST services. Currently the Launch
 * service is accessed using JAX-RS. Other services (e.g. Rockets, Payloads)
 * could be added and then made available here. Specifications for r/SpaceX
 * services can be found at:
 * 
 * https://documenter.getpostman.com/view/2025350/RWaEzAiG?version=latest
 * 
 * @author Patrick Paulin
 */

public class SpaceXFacade {

	private LaunchService launchService;

	public LaunchService getLaunchService() {
		return launchService;
	}

	void setLaunchService(LaunchService launchService) {
		this.launchService = launchService;
	}
}
