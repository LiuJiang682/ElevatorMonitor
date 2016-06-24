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
		LOGGER.info("inside index");
		return "index";
	}
	
	@RequestMapping("/elvMon") 
	public ModelAndView elevator1() {
		LOGGER.info("inside elevator1");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		String str = "Hello World1!";
		mav.addObject("message1", str);
		mav.addObject("message2", "Hello World2!");
		return mav;
	}
}
