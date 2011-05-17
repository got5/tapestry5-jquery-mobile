package org.got5.tapestry5.jquery.mobile.data;

public class GeoPosition {

	private String heading, 
					altitude, 
					latitude, 
					accuracy,
					longitude, 
					speed, 
					altitudeAccuracy, 
					timestamp;

	
	
	public GeoPosition(String heading, String altitude, String latitude,
			String accuracy, String longitude, String speed,
			String altitudeAccuracy, String timestamp) {
		super();
		this.heading = heading;
		this.altitude = altitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
		this.longitude = longitude;
		this.speed = speed;
		this.altitudeAccuracy = altitudeAccuracy;
		this.timestamp = timestamp;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getAltitudeAccuracy() {
		return altitudeAccuracy;
	}

	public void setAltitudeAccuracy(String altitudeAccuracy) {
		this.altitudeAccuracy = altitudeAccuracy;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "GeoPosition [accuracy=" + accuracy + ", altitude=" + altitude
				+ ", altitudeAccuracy=" + altitudeAccuracy + ", heading="
				+ heading + ", latitude=" + latitude + ", longitude="
				+ longitude + ", speed=" + speed + ", timestamp=" + timestamp
				+ "]";
	}
	
	
}
