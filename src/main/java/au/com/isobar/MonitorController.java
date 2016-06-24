package au.com.isobar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MonitorController {

	private static final Logger LOGGER = Logger.getLogger(MonitorController.class);

	@RequestMapping("/")
	public String index() {
		LOGGER.info("inside index");
		return "index";
	}
}
