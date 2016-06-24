package au.com.isobar.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import au.com.isobar.model.ElevatorStatus;

@Component(value="monitorServices")
public class TwoElevatorMonitorServices implements MonitorService {

	private List<ElevatorStatus> status;
	
	@Override
	public ElevatorStatus getElevatorStatus(int index) {
		if (null == status) {
			status = new ArrayList<>();
			status.add(new ElevatorStatus("up", "2", "locked"));
			status.add(new ElevatorStatus("Down", "3", "unlock"));
		}
		return status.get(index);
	}

}
