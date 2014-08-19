package models;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import play.Logger;
import play.db.jpa.Blob;
import play.db.jpa.JPA;
import play.db.jpa.Model;

@Entity
@Table(name="cas_stop")
public class Stop extends Model{
	
	@Expose
	private String stopName;
	
	@Expose
	private String stopDetails;
	
	@Expose
	private Double locationLat;
	
	@Expose
	private Double locationLon;
	
	@Expose
	@Transient 
	private Long totalCheckin;
	
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
	
	
	@PostLoad
	protected void initSomeTransientValues() {
		this.totalCheckin = getNumberofCheckinsForThisLocation(this);
	}
	
	public static Long getNumberofCheckinsForThisLocation(Stop currentStop){
		Long totalCheckins = 10L;
		double  delta = .000704;
		Double currentStopLat = currentStop.getLocationLat();
		Double currentStopLatUp = currentStopLat + delta;
		Double currentStopLatDown = currentStopLat - delta;
		
		Double currentStopLon = currentStop.getLocationLon();
		Double currentStopLonUp = currentStopLon + delta;
		Double currentStopLonDown = currentStopLon - delta;
		
		String sql = "select count(*) from cas_user_checkin s where s.checkinLocationLat between " +
				currentStopLatDown +" and "+ currentStopLatUp + " and "
				+ " s.checkinLocationLon between " + currentStopLonDown +" and "+ currentStopLonUp + "";
		
		Logger.info("getNumberofCheckinsForThisLocation sql ====== " + sql);
		 
		Query query = JPA.em().createNativeQuery(sql);
		BigInteger totalCheckinsBig =  ((BigInteger) query.getSingleResult()); 
		totalCheckins = totalCheckinsBig.longValue();
	   
	    Logger.info("getNumberofCheckinsForThisLocation totalCheckins ====== " + totalCheckins);
		
		return totalCheckins;
		
	}
	
	@Override
	public String toString() {
		return  getId() +" " +  getStopName() +" " + getStopDetails();
	}
}
