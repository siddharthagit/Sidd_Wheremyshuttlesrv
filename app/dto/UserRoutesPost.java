package dto;

import com.google.gson.annotations.Expose;

public class UserRoutesPost {
	@Expose
	private String userEmail;
	@Expose
	private String[] routeDetailIds;
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String[] getRouteDetailIds() {
		return routeDetailIds;
	}
	public void setRouteDetailIds(String[] routeDetailIds) {
		this.routeDetailIds = routeDetailIds;
	}
	
	
}
