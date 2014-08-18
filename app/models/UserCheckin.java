package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_user_checkin")
public class UserCheckin extends Model{
	
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@Expose
	@ManyToOne
	@JoinColumn(name="completeroute_id", referencedColumnName="id")
	private CompleteRouteDetail routeDetails;
	
	@Expose
	private Date checkinTime;
	
	@Expose
	private Double checkinLocationLat;
	
	@Expose
	private Double checkinLocationLon;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCheckinTime() {
		return checkinTime;
	}
	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}
	public Double getCheckinLocationLat() {
		return checkinLocationLat;
	}
	public void setCheckinLocationLat(Double checkinLocationLat) {
		this.checkinLocationLat = checkinLocationLat;
	}
	public Double getCheckinLocationLon() {
		return checkinLocationLon;
	}
	public void setCheckinLocationLon(Double checkinLocationLon) {
		this.checkinLocationLon = checkinLocationLon;
	}
	public CompleteRouteDetail getRouteDetails() {
		return routeDetails;
	}
	public void setRouteDetails(CompleteRouteDetail routeDetails) {
		this.routeDetails = routeDetails;
	}
	
	///extra methods
	public static UserCheckin getLastCheckinLoc(long userId){
		UserCheckin  lastCheckin = UserCheckin.find(" order by checkinTime desc").first();
		return lastCheckin;
	}
	
	
}
