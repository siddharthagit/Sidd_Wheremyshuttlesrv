package dto;

import com.google.gson.annotations.Expose;

public class UserDTO {
	@Expose
	private String userName;
	
	private String userPMFKey;
	
	@Expose
	private String userEmail;
	
	@Expose
	private Boolean notification;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPMFKey() {
		return userPMFKey;
	}

	public void setUserPMFKey(String userPMFKey) {
		this.userPMFKey = userPMFKey;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Boolean getNotification() {
		return notification;
	}

	public void setNotification(Boolean notification) {
		this.notification = notification;
	}
	
	
}
