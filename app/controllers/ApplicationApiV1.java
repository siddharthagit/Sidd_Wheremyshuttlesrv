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
	
	
	public static void getCompleteRouteDetailStatic(Long routeId) {
		Logger.info("getUserRouteDetailsStatic " + routeId);
		
		CompleteRouteDetail routeDetails = CompleteRouteDetail.findById(routeId);
		
		renderJSON(gson.toJson(routeDetails));
	}
	
	public static void getCompleteRouteDetailList() {
		Logger.info("getUserRouteDetailsStatic ");
		
		List<CompleteRouteDetail> routeDetails = CompleteRouteDetail.findAll();
		
		renderJSON(gson.toJson(routeDetails));
	}
	
	
	
	/**
	 * For a Route get all the Stops with gps Tag, number of checkin in each stop
	 * It should return
	 * Stops[name,lat/lon,eta,checkins]
	 * Dynamic[] 
	 * 
	 * from the userid(email ID) get all route details and get the stops and checkins
	 */
	
	public static void getMapStaticLocationsForaRoute(String emailId) {
		Logger.info("getMapStaticLocationsForaRoute" + emailId);
		
		Query query = JPA.em().createQuery("select s from User s where s.userEmail ='" +emailId+"'");
	    User dbUser = (User)query.getSingleResult();
	    Long dbUserId = dbUser.getId();
	    Logger.info("getMapStaticLocationsForaRoute userId from db " + dbUserId);
	    
	    //get the first subscription
	    Query subsriptionQ = JPA.em().createQuery("select s from UserRouteSubscription s where s.user.id=" +dbUserId);
	    UserRouteSubscription dbUserSubscription = (UserRouteSubscription)subsriptionQ.getSingleResult();
	    
	    Long dbUserRouteDetailsId = dbUserSubscription.getRouteDetails().getId();
	    
	    Logger.info("getMapStaticLocationsForaRoute dbUserSubId from db " + dbUserRouteDetailsId);
	    
	    
	    
	    
		//renderJSON(gson.toJson(lastCheckinLoc));
	}
	
	

	//POST
	/**
	 * Post a User Check in Location to System
	 */
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
		
		myModel.refresh();
		Logger.info("checkinUser " + myModel.getId());
		
		renderJSON(gson.toJson(myModel));
		
	}

	
}
