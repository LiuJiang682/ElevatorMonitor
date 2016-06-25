package au.com.isobar;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import au.com.isobar.model.ElevatorStatus;
import au.com.isobar.services.MonitorService;

@RestController
public class MonitorController {

	private static final Logger LOGGER = Logger.getLogger(MonitorController.class);

	private static final int FIRST = 0;

	private static final int SECOND = 1;

	@Autowired
	@Qualifier("monitorServices")
	private MonitorService monitorService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		LOGGER.info("index called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/elvMon") 
	public ModelAndView elevatorMonitor() {
		LOGGER.info("elevatorMonitor called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		List<ElevatorStatus> messages = new ArrayList<>();
		
		messages.add(monitorService.getElevatorStatus(FIRST));
		messages.add(monitorService.getElevatorStatus(SECOND));
		mav.addObject("messages", messages);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST) 
	public String update(@RequestBody ElevatorStatus status) {
		LOGGER.info("update called with " + status);
		boolean success = monitorService.update(status);
		return success ? "Ok" : "Fail";
	}

}
