package com.modumind.spacex.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.modumind.spacex.service.model.Launch;

class LaunchServiceTest {

	private static ServiceTracker<LaunchService, LaunchService> launchServiceTracker;
	
	@BeforeAll
	public static void beforeAll() throws Exception {
		
		Bundle bundle = FrameworkUtil.getBundle(LaunchServiceTest.class);
		
		List<Bundle> bundles = Arrays.asList(bundle.getBundleContext().getBundles());
		for (Bundle b : bundles) {
			if (b.getSymbolicName().equals("org.glassfish.jersey.core.jersey-common")) {
				b.start();
			}
		}
		
		for (Bundle b : bundles) {
			if (b.getSymbolicName().equals("org.glassfish.jersey.inject.jersey-hk2")) {
				b.start();
			}
		}
		
		for (Bundle b : bundles) {
			if (b.getSymbolicName().equals("org.eclipse.ecf.osgi.services.distribution")) {
				b.start();
			}
		}
		
		launchServiceTracker = new ServiceTracker<LaunchService, LaunchService>(bundle.getBundleContext(), LaunchService.class, null);
		launchServiceTracker.open();
		launchServiceTracker.waitForService(500);
	}

	@AfterAll
	public static void afterAll() {
		launchServiceTracker.close();
	}
	
	@Test
	void test() {
		LaunchService launchService = launchServiceTracker.getService();
		List<Launch> launches = launchService.getLaunches();
		assertTrue(launches.size() > 0);
	}
	
	
}
