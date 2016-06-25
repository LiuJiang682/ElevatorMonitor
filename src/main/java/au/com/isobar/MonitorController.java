package au.com.isobar;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import au.com.isobar.model.ElevatorStatus;
import au.com.isobar.services.MonitorService;

@Controller
public class MonitorController {

	private static final Logger LOGGER = Logger.getLogger(MonitorController.class);

	private static final int FIRST = 0;

	private static final int SECOND = 1;

	@Autowired
	@Qualifier("monitorServices")
	private MonitorService monitorService;
	
	@RequestMapping("/")
	public String index() {
		LOGGER.info("index called");
		return "index";
	}
	
	@RequestMapping("/elvMon") 
	public ModelAndView elevatorMonitor() {
		LOGGER.info("elevatorMonitor called");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		List<ElevatorStatus> messages = new ArrayList<>();
//		messages.add(new ElevatorStatus("up", "2", "locked"));
//		messages.add(new ElevatorStatus("Down", "3", "unlock"));
		LOGGER.warn("monitorService " + monitorService);
		
		messages.add(monitorService.getElevatorStatus(FIRST));
		messages.add(monitorService.getElevatorStatus(SECOND));
		mav.addObject("messages", messages);
		return mav;
	}

}
