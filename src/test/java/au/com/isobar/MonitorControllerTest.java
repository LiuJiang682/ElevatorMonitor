package au.com.isobar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

/**
 * Test class for MonitorController class
 *
 */
public class MonitorControllerTest {

	/**
	 * Given the application started
	 * When the user entered the URL: http://localhost:8080/
	 * Then the controller should return index as string
	 */
	@Test
	public void whenDefaultURLEnteredThenIndexShouldReturn() {
		//Given the app started
		MonitorController controller = new MonitorController();
		//When the user enter http://localhost:8080/
		String jspName = controller.index();
		//Then index should be return
		assertEquals("index", jspName);
	}
	
	/**
	 * Given the application started
	 * When the user entered the URL: http://localhost:8080/elvMon
	 * Then the controller should return 2 message
	 */
	@Test
	public void whenelvMonURLEnteredThen2MessageShouldReturn() {
		//Given the app started
		MonitorController controller = new MonitorController();
		//When the user enter http://localhost:8080/elvMon
		ModelAndView mav = controller.elevatorMonitor();
		//Then 2 message should be return
		assertNotNull(mav);
		Map<String, Object> messages = mav.getModel();
		assertNotNull(messages);
		assertTrue(2 == messages.size());
	}
}
