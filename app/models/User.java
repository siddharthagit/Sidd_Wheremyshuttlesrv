package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
@Table(name="cas_users")
public class User extends Model{
	
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
	
	@Override
	public String toString() {
		return getUserPMFKey() + " - " + getUserEmail() + " - " + getUserName();
	}
	
}
