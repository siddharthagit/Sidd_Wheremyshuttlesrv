package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_stop")
public class Stop extends Model{
	
	private String stopName;
	private String stopDetails;
	private Double locationLat;
	private Double locationLon;
	
	
	
	
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	public String getStopDetails() {
		return stopDetails;
	}
	public void setStopDetails(String stopDetails) {
		this.stopDetails = stopDetails;
	}
	public Double getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}
	public Double getLocationLon() {
		return locationLon;
	}
	public void setLocationLon(Double locationLon) {
		this.locationLon = locationLon;
	}
	
	
	
}
