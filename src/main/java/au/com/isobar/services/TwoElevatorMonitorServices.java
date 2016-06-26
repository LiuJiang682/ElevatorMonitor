package au.com.isobar.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import au.com.isobar.model.ElevatorStatus;

@Component(value="monitorServices")
public class TwoElevatorMonitorServices implements MonitorService {
	
	private static final Logger LOGGER = Logger.getLogger(TwoElevatorMonitorServices.class);

	private static final String LOCK_STATUS_TAG = "lockStatus=";

	private static final String FLOOR_TAG = "floor=";

	private static final String MOVING_TAG = "moving=";

	private static final String ID_TAG = "Id=";

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static final String CHAR_SET_UTF_8 = "UTF-8";
	private static final String FIELD_SEPARATOR = ",";

	private static final int ZERO = 0;

	private static final int ONE = 1;

	private static final int TWO = 2;

	private static final String EMPTY = "";

	private static final int THREE = 3;

	@Value("${data.file.name}")
    private String fileName;
	
	private File file;
	
	@PostConstruct
	public void init() { 
		if (StringUtils.isBlank(fileName)) {
			throw new RuntimeException("fileName cannot be blank!");
		}
		try {
			file = new File(this.fileName); 
			LOGGER.warn("File path: " + file.getAbsolutePath());
			if (!file.exists()) {
				file.createNewFile();
			}
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e.getMessage(), e);
		}
	}
	
	@Override
	public ElevatorStatus getElevatorStatus(int index) {
		LOGGER.info("enter with index " + index);
		ElevatorStatus status = new ElevatorStatus("", "", "", "");
		String idString = ID_TAG + index;
		BufferedReader bufferedReader = null;
		try {
			FileInputStream fis = new FileInputStream(this.file);
			bufferedReader = new BufferedReader(new InputStreamReader(fis, CHAR_SET_UTF_8));
			String line = bufferedReader.readLine(); 
			while((StringUtils.isNotBlank(line)) && (!line.startsWith(idString))){
				line = bufferedReader.readLine(); 
			} 
			if ((StringUtils.isNotBlank(line)) && (line.startsWith(idString))) {
				status = doConversion(line);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
				}
				bufferedReader = null;
			}
		}
		LOGGER.info("Returning " + status);
		return status;
	}

	protected ElevatorStatus doConversion(String line) {
		String[] strings = line.split(FIELD_SEPARATOR);
		String idString = strings[ZERO].trim();
		String moving = strings[ONE].trim();
		String floor = strings[TWO].trim();
		String lockStatus = strings[THREE].trim();
		idString = idString.replace(ID_TAG, EMPTY);
		moving = moving.replace(MOVING_TAG, EMPTY);
		floor = floor.replace(FLOOR_TAG, EMPTY);
		lockStatus = lockStatus.replace(LOCK_STATUS_TAG, EMPTY);
		return new ElevatorStatus(idString, moving, floor, lockStatus);
	}
	
	public String getFilieName() {
		return this.fileName;
	}

	@Override
	public boolean update(ElevatorStatus status) {
		boolean success = false;
		boolean updated = false;
		try {
			String tempFileName = fileName + System.currentTimeMillis();
			File tempFile = new File(tempFileName); 
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			FileInputStream fis = new FileInputStream(file);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis, CHAR_SET_UTF_8));
			
			String lineToUpdate = toBareContent(status);
			String idString = ID_TAG + status.getLiftId();
			String currentLine;
			while(null != (currentLine = bufferedReader.readLine())) {
				String trimmedLine = currentLine.trim();
				if (trimmedLine.startsWith(idString)) {
					currentLine = lineToUpdate;
					updated = true;
				}
				writer.write(currentLine + LINE_SEPARATOR);
			}
			
			//Insert into the end of file.
			if (!updated) {
				writer.write(lineToUpdate + LINE_SEPARATOR);
			}
			
			//Done! Clean up resource. 
			writer.flush();
			writer.close();
			bufferedReader.close();
			fis.close();
			
			// Replace the old file with temp file.
			success = file.delete();
			if (success) {
				success = tempFile.renameTo(file);
			}
			// Points the file and inputStream to
			// the new file.
			if (success) {
				file = new File(fileName);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	public String toBareContent(ElevatorStatus status) {
		StringBuilder buf = new StringBuilder("Id=");
		buf.append(status.getLiftId());
		buf.append(FIELD_SEPARATOR);
		buf.append(MOVING_TAG);
		buf.append(status.getMoving());
		buf.append(FIELD_SEPARATOR);
		buf.append(FLOOR_TAG);
		buf.append(status.getFloor());
		buf.append(FIELD_SEPARATOR);
		buf.append(LOCK_STATUS_TAG);
		buf.append(status.getLockStatus());
		return buf.toString();
	}

}
