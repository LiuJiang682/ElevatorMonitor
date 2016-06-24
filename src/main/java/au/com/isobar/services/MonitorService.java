package au.com.isobar.services;

import au.com.isobar.model.ElevatorStatus;

public interface MonitorService {

	ElevatorStatus getElevatorStatus(int first);

}
