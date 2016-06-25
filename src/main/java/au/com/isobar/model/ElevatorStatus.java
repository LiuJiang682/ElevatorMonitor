package au.com.isobar.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ElevatorStatus implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 6837956163441524420L;
	
	private String liftId;
	private String moving;
	private String floor;
	private String lockStatus;
	
	public ElevatorStatus() {
		liftId = "";
		moving = "";
		floor ="";
		lockStatus = "";
	}
	
	public ElevatorStatus(final String liftId,
			final String moving, 
			final String floor, 
			final String lockStatus) {
		this.liftId = liftId;
		this.moving = moving;
		this.floor = floor;
		this.lockStatus = lockStatus;
	}

	public String getLiftId() {
		return liftId;
	}

	public String getMoving() {
		return moving;
	}

	public String getFloor() {
		return floor;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLiftId(String liftId) {
		this.liftId = liftId;
	}

	public void setMoving(String moving) {
		this.moving = moving;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
