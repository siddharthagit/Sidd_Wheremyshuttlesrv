package dto;



import com.google.gson.annotations.Expose;

public class CompleteRouteDetailsDTO {

	@Expose 
	private Long routeDetailsId;
	
	@Expose
	private String routeName;
	
	@Expose
	private String startTime;
	
	@Expose
	private String endTime;

	public Long getRouteDetailsId() {
		return routeDetailsId;
	}

	public void setRouteDetailsId(Long routeDetailsId) {
		this.routeDetailsId = routeDetailsId;
	}

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
	
	
	
}
