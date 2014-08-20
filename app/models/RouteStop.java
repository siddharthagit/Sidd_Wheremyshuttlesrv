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

	//extra methods
	
	@PostLoad
	protected void initSomeTransientValues() {
		this.stopName = getCurrentStop().getStopName();
		this.stopDetails = getCurrentStop().getStopDetails();
		this.locationLat = getCurrentStop().getLocationLat();
		this.locationLon = getCurrentStop().getLocationLon();
		this.cta = this.eta;
	}
	
}
