package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_completeroute")
public class CompleteRouteDetail extends Model{
	
	@Expose 
	@Transient
	private Long routeDetailsId;
	
	@Expose
	private String routeName;
	
	@Expose
	private String startTime;
	
	@Expose
	private String endTime;
	
	
	@OneToMany(mappedBy="route")
	private List<RouteStop> routeStops;

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}



	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<RouteStop> getRouteStops() {
		return routeStops;
	}

	public void setRouteStops(List<RouteStop> routeStops) {
		this.routeStops = routeStops;
	}
	
	
	@Override
	public String toString() {
		return "RouteDetails"+ getId() +" - "+getRouteName();
	}
	
	//etra methods
	
	@PostLoad
	protected void initSomeTransientValues() {
		this.routeDetailsId = getId();
	}
	
}
