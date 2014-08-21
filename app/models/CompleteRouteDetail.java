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
	
	private int direction;
	private String directionStr;
	
	@Expose
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
	
	

	public Long getRouteDetailsId() {
		return routeDetailsId;
	}

	public void setRouteDetailsId(Long routeDetailsId) {
		this.routeDetailsId = routeDetailsId;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	//etra methods
	

	@Override
	public String toString() {
		return "##"+ getId() +" - "+getRouteName() + " " + getDirectionStr();
	}
	
	public String getDirectionStr(){
		if(getDirection() ==0) return "To Office";
		else return "From Office";
	}
	
	@PostLoad
	protected void initSomeTransientValues() {
		this.routeDetailsId = getId();
		this.directionStr = getDirectionStr();
	}
	
}
