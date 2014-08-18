package controllers;

import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

import models.CompleteRouteDetail;
import models.Stop;
import models.User;
import models.UserCheckin;
import models.UserRouteSubscription;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Http.Request;



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
	

	//POST
	
	public static void checkinUser() {
		Logger.info("checkinUser" + request);
		UserCheckin myModel = new GsonBuilder().create().fromJson(new InputStreamReader(request.body), UserCheckin.class);
		Logger.info("checkinUser " + request.body);
		Logger.info("checkinUser Content Type " + request.contentType);
		Logger.info("checkinUser Lat" + myModel.getCheckinLocationLat());
		
		Long userIdFromReq = myModel.getUserId();
		String userEmailIdFromReq = myModel.getUserEmailId();
		Long routeIdFromReq = myModel.getRouteDetailsId();
		
		Logger.info("checkinUser userId,EmailID,routeDetailsId " + userIdFromReq + "  " + userEmailIdFromReq +"  " + routeIdFromReq);
		
		Query query = JPA.em().createQuery("select s from User s where s.userEmail ='" +userEmailIdFromReq+"'");
	    User dbUser = (User)query.getSingleResult();
	    
	    Query query2 = JPA.em().createQuery("select s from CompleteRouteDetail s where s.id =" +routeIdFromReq);
	    CompleteRouteDetail dbCompleteRouteDetail = (CompleteRouteDetail)query2.getSingleResult();
	    
	    Logger.info("checkinUser dbUser = " + dbUser);
	    Logger.info("checkinUser CompleteRouteDetail = " + dbCompleteRouteDetail);
	
		myModel.setUser(dbUser);
		myModel.setRouteDetails(dbCompleteRouteDetail);
		myModel.save();
		
		Logger.info("checkinUser " + myModel.getId());
		
	}

	
}
