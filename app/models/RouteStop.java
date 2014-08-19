package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@OneToOne
	private Stop currentStop;
	
	@Expose
	private String eta;
	
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

	
}
