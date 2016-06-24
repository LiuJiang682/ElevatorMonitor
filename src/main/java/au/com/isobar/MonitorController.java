package au.com.isobar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MonitorController {

	private static final Logger LOGGER = Logger.getLogger(MonitorController.class);

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
		String str = "Hello World1!";
		mav.addObject("message1", str);
		mav.addObject("message2", "Hello World2!");
		return mav;
	}
}
