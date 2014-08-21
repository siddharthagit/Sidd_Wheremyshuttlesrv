package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
//@Table(name="cas_user_route_subscription")

@Table(
	    name="cas_user_route_subscription", 
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"routeDetails_id", "user_id"})
	)
public class UserRouteSubscription extends Model{
	
	
	
	@ManyToOne
	private User user;
	
	@Expose 
	@ManyToOne
	private CompleteRouteDetail routeDetails;
	
	
	//For Rest Post
	
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CompleteRouteDetail getRouteDetails() {
		return routeDetails;
	}
	public void setRouteDetails(CompleteRouteDetail routeDetails) {
		this.routeDetails = routeDetails;
	}
	
	
	
}
