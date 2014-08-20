package dto;


import com.google.gson.annotations.Expose;


public class UserRouteSubscriptionDTO {
	@Expose
	private UserDTO user;
	
	@Expose 
	private CompleteRouteDetailsDTO routeDetails;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CompleteRouteDetailsDTO getRouteDetails() {
		return routeDetails;
	}

	public void setRouteDetails(CompleteRouteDetailsDTO routeDetails) {
		this.routeDetails = routeDetails;
	}
	
	
	
		
}
