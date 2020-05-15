package com.modumind.spacex.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // r/SpaceX uses underscored field names
@JsonIgnoreProperties(ignoreUnknown = true)
public class Launch {

	private String flightNumber;
	private String missionName;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	
	public String getMissionName() {
		return missionName;
	}
}
