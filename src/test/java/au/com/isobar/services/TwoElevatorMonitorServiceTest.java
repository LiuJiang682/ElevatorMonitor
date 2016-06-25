package au.com.isobar.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.isobar.ElevatorMonitorConfig;
import au.com.isobar.model.ElevatorStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ElevatorMonitorConfig.class})
public class TwoElevatorMonitorServiceTest {

	@Autowired
	TwoElevatorMonitorServices service;
	
	private ElevatorStatus status;
	
	
//	@After
//	public void clearUp() {
//		String fileName = service.getFilieName();
//		File file = new File(fileName); 
//		String parentString = file.getAbsolutePath().replace(fileName, EMPTY);
//		File parent = new File(parentString);
//		File[] toBeDeleted = parent.listFiles();
//		if (null != toBeDeleted) {
//			for (File f : parent.listFiles()) {
//				if (f.getName().startsWith(fileName)) {
//					f.delete(); 
//				}
//			}
//		}
//	}
	
	/**
	 * Given the user can access the TwoElevatorMonitorServices class
	 * When constructor called
	 * Then the fileName should be exist
	 */
	@Test
	public void whenConstructorCalledThenFileNameShouldBeExist() {
		//Given the user can access the the TwoElevatorMonitorServices
		//When constructor called
		//Then the DataSource should be exist
		assertNotNull(service);
		assertEquals("src/test/resources/dataFile", service.getFilieName());
	}
	
	/**
	 * Given the user can access the TwoElevatorMonitorServices class
	 * When the update method called
	 * Then the new record created
	 * @throws IOException 
	 */
	@Test
	public void whenElevatorStatusProvidedThenRecordShouldUpdated() throws IOException {
		givenElevatorStatus();
		//Given the user can access the TwoElevatorMonitorServices class
		//When the update method called
		boolean flag = service.update(status);
		//Then a record should created
		assertTrue(flag);
		File file = new File(service.getFilieName());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String line1 = bufferedReader.readLine();
		assertNotNull(line1); 
		assertEquals("Id=0,moving=up,floor=3,lockStatus=lock", line1);
		String line2 = bufferedReader.readLine();
		assertNull(line2);
		bufferedReader.close();
		
		ElevatorStatus another = new ElevatorStatus("0", "down", "3", "lock");
		//When the update method called
		boolean newflag = service.update(another);
		assertTrue(newflag);
		File newfile = new File(service.getFilieName());
		BufferedReader newbufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newfile), "UTF-8"));
		String line3 = newbufferedReader.readLine();
		assertNotNull(line3); 
		assertEquals("Id=0,moving=down,floor=3,lockStatus=lock", line3);
		String line4 = newbufferedReader.readLine();
		assertNull(line4);
		newbufferedReader.close();
	}
	
	/**
	 * Given the user can access the TwoElevatorMonitorServices class
	 * When the toBareContent method called
	 * Then a string return
	 */
	@Test
	public void whenElevatorStatusProvidedThenAContentStringShouldReturn() {
		givenElevatorStatus();
		String content = service.toBareContent(status);
		assertNotNull(content);
		assertEquals("Id=0,moving=up,floor=3,lockStatus=lock", content);
	}
	
	/**
	 * Given the user can access the TwoElevatorMonitorServices class
	 * When the get method called
	 * Then the ElevatorStatus object return
	 */
	@Test
	public void whenGetElevatorStatusWithIDZeroCalledThenFirstElevatorStatusShouldBeReturn() {
		//Given the user can access the TwoElevatorMonitorServices class
		//When the getElevatorStatus method called
		givenElevatorStatus();
		service.update(status);
		
		ElevatorStatus retrieve = service.getElevatorStatus(0);
		//Then first object should be return
		assertNotNull(retrieve);
		assertEquals("0", retrieve.getLiftId());
		assertEquals("up", retrieve.getMoving());
		assertEquals("3", retrieve.getFloor());
		assertEquals("lock", retrieve.getLockStatus());
	}
	
	/**
	 * Given the user can access the TwoElevatorMonitorServices class
	 * When the doConversion method called
	 * Then the ElevatorStatus object return
	 */
	@Test
	public void whenDoConversionCalledThenElevatorStatusShouldBeReturn() {
		//Given the user can access the TwoElevatorMonitorServices class
		givenElevatorStatus();
		String line = service.toBareContent(status);
		//When the DoConversion method called
		ElevatorStatus retrieve = service.doConversion(line);
		//Then first object should be return
		assertNotNull(retrieve);
		assertEquals("0", retrieve.getLiftId());
		assertEquals("up", retrieve.getMoving());
		assertEquals("3", retrieve.getFloor());
		assertEquals("lock", retrieve.getLockStatus());
	}

	private void givenElevatorStatus() {
		status = new ElevatorStatus("0", "up", "3", "lock");
	}
}
