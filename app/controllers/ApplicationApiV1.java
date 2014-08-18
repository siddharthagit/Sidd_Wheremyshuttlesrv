package controllers;

import java.util.List;

import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.CompleteRouteDetail;
import models.Stop;
import models.User;
import models.UserCheckin;
import models.UserRouteSubscription;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Controller;



public class ApplicationApiV1 extends Controller {

	public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	
	public static void getUserList() {
		Logger.info("getUserList");
		List<User> s = User.findAll();	
		renderJSON(s);
	}
	
	public static void getUserDetails(Long userId) {
		Logger.info("getUserDetails" + userId);
		User s = User.findById(userId);	
		renderJSON(s);
	}
	
	public static void getUserRouteSubsciption(Long userId) {
		Logger.info("getUserSubsciption" + userId);
		
		Query query = JPA.em().createQuery("select s from UserRouteSubscription s where s.user.id=" +userId);
	    List<UserRouteSubscription> routeDetails = query.getResultList();
	    
		//CompleteRouteDetail s = CompleteRouteDetail.find(" find ")(userId);	
		Logger.info("getUserSubsciption" + routeDetails);
		renderJSON(gson.toJson(routeDetails));
	}
	
	
	
	public static void getUserLastCheckinLocation(Long userId) {
		Logger.info("getUserLastCheckinLocation" + userId);
		UserCheckin lastCheckinLoc = UserCheckin.getLastCheckinLoc(userId);
		renderJSON(gson.toJson(lastCheckinLoc));
	}
	
	
	
}
