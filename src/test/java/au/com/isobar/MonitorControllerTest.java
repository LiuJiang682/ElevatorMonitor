package au.com.isobar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.servlet.ModelAndView;

import au.com.isobar.model.ElevatorStatus;
import au.com.isobar.services.MonitorService;

/**
 * Test class for MonitorController class
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MonitorController.class})
public class MonitorControllerTest {
	
	@Mock
	MonitorService mockService;

	@InjectMocks 
	MonitorController controller;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
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
		//Given the app started and mock services
		when(mockService.getElevatorStatus(Matchers.anyInt())).thenReturn(new ElevatorStatus("","", "", ""));
		//When the user enter http://localhost:8080/elvMon
		ModelAndView mav = controller.elevatorMonitor();
		//Then 2 message should be return
		assertNotNull(mav);
		Map<String, Object> messages = mav.getModel();
		assertNotNull(messages);
		assertTrue(1 == messages.size());
		Object object = messages.get("messages");
		assertNotNull(object);
		assertTrue(object instanceof List);
		List<ElevatorStatus> elevatorStatuses = (List<ElevatorStatus>)object;
		assertTrue(2 == elevatorStatuses.size());
		verify(mockService, times(2)).getElevatorStatus(Matchers.anyInt());
	}
}
