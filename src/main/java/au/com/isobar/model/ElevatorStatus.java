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
	
	private String moving;
	private String floor;
	private String lockStatus;
	
	public ElevatorStatus(final String moving, final String floor, final String lockStatus) {
		this.moving = moving;
		this.floor = floor;
		this.lockStatus = lockStatus;
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
