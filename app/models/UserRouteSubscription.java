package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_user_route_subscription")
public class UserRouteSubscription extends Model{
	
	private User user;
	private CompleteRouteDetail routeDetails;
	
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
