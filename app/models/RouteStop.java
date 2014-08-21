package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.LocalTime;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_routestop")
public class RouteStop extends Model{
	
	@ManyToOne
	@JoinColumn(name="routedetails_id",referencedColumnName="id")
	private CompleteRouteDetail route;
	
	@Expose
	@Transient
	private String stopName;
	
	@Expose
	@Transient
	private String stopDetails;
	
	@Expose
	@Transient
	private Double locationLat;
	
	@Expose
	@Transient
	private Double locationLon;
	
	//@Expose
	@OneToOne
	private Stop currentStop;
	
	@Expose()
	private String eta;
	
	@Expose()
	private String cta;
	
	@Expose()
	private Long totalCheckin = 1L;
	
	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public Stop getCurrentStop() {
		return currentStop;
	}

	public void setCurrentStop(Stop currentStop) {
		this.currentStop = currentStop;
	}

	public CompleteRouteDetail getRoute() {
		return route;
	}

	public void setRoute(CompleteRouteDetail route) {
		this.route = route;
	}

	
	
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

	public String getCta() {
		return cta;
	}

	public void setCta(String cta) {
		this.cta = cta;
	}
	
	
	
	//extra methods
	
	

	@PostLoad
	protected void initSomeTransientValues() {
		this.stopName = getCurrentStop().getStopName();
		this.stopDetails = getCurrentStop().getStopDetails();
		this.locationLat = getCurrentStop().getLocationLat();
		this.locationLon = getCurrentStop().getLocationLon();
		this.cta = this.eta;
		this.totalCheckin = getCurrentStop().getTotalCheckin();
	}
	
}
