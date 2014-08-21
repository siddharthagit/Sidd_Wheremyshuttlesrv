package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_user_checkin")
public class UserCheckin extends Model{
	
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name="completeroute_id", referencedColumnName="id")
	private CompleteRouteDetail routeDetails;
	
	@Expose
	private Date checkinTime;
	
	@Expose
	private Double checkinLocationLat;
	
	@Expose
	private Double checkinLocationLon;
	
	
	@Expose
	@Transient 
	private Long routeDetailsId= null;
	
	@Expose
	@Transient 
	private Long checkinId= null;
	
	@Expose
	@Transient 
	private Long userId= null;

	@Expose
	@Transient 
	private String userEmailId= null;
	
	
	public Long getRouteDetailsId() {
		return this.routeDetailsId;
	}
	public void setRouteDetailsId(Long routeDetailsId) {
		this.routeDetailsId = getRouteDetails().getId();
	}
	
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
	
	
	
	
	public Long getCheckinId() {
		return checkinId;
	}
	public void setCheckinId(Long checkinId) {
		this.checkinId = checkinId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
	
	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	
	
	
	///extra methods
	public static UserCheckin getLastCheckinLoc(long userId){
		UserCheckin  lastCheckin = UserCheckin.find(" order by checkinTime desc").first();
		return lastCheckin;
	}
	
	
	@PostLoad
	protected void initSomeTransientValues() {
	    //Likely some more complex logic to figure out value,
	    //if it is this simple, just set it directly in field declaration.
		this.routeDetailsId = getRouteDetails().getId();
		this.checkinId = getId();
		this.userId = user.getId();
		this.userEmailId = user.getUserEmail();
	}
	
	
}
