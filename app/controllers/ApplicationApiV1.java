package controllers;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

import dto.CompleteRouteDetailsDTO;
import dto.UserDTO;
import dto.UserRouteSubscriptionDTO;
import dto.UserRoutesPost;

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
	
	public static void getUserRouteSubsciption(String userEmailID) {
		Logger.info("getUserSubsciption" + userEmailID);
		
		Query query = JPA.em().createQuery("select s from UserRouteSubscription s where s.user.userEmail='" +userEmailID +"'");
	    List<UserRouteSubscription> routeDetails = query.getResultList();
	    
	 
		//CompleteRouteDetail s = CompleteRouteDetail.find(" find ")(userId);	
		Logger.info("getUserSubsciption" + routeDetails);
		renderJSON(gson.toJson(routeDetails));
	}
	
	
	public static void getUserRouteSubsciption2(String userEmailID) {
		Logger.info("getUserSubsciption" + userEmailID);
		
		Query query = JPA.em().createQuery("select s from UserRouteSubscription s where s.user.userEmail='" +userEmailID +"'");
	    List<UserRouteSubscription> routeDetails = query.getResultList();
	    
	    List<UserRouteSubscriptionDTO>  userRoutesDTO= new ArrayList<UserRouteSubscriptionDTO>();
	   
	    CompleteRouteDetailsDTO routeDetailsDTO = new CompleteRouteDetailsDTO();
	    
	    for(UserRouteSubscription sub : routeDetails){
	    	
	    	UserRouteSubscriptionDTO ursDTO = new UserRouteSubscriptionDTO();
	    	UserDTO userDTO = new UserDTO();
	    	
	    	CompleteRouteDetail mcompleteRouteDetails = sub.getRouteDetails();
	    	routeDetailsDTO.setRouteDetailsId(mcompleteRouteDetails.getId());
	    	routeDetailsDTO.setRouteName(mcompleteRouteDetails.getRouteName());
	    	routeDetailsDTO.setStartTime(mcompleteRouteDetails.getStartTime());
	    	routeDetailsDTO.setEndTime(mcompleteRouteDetails.getEndTime());
	    	
	    	userDTO.setUserEmail(sub.getUser().getUserEmail());
	    	
	    	ursDTO.setRouteDetails(routeDetailsDTO);
	    	ursDTO.setUser(userDTO);
	    	
	    	userRoutesDTO.add(ursDTO);
	    }
		//CompleteRouteDetail s = CompleteRouteDetail.find(" find ")(userId);	
		Logger.info("getUserSubsciption" + routeDetails);
		renderJSON(gson.toJson(userRoutesDTO));
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
	 * 
	 * Steps
	 * Get user from email id
	 * Get RouteDetails for user
	 * Get current checkins and update the cta
	 */
	
	public static void getCompleteMapDetails(String userEmailID) {
		Logger.info("getCompleteMapDetails emailId = " + userEmailID);
		
		Long dbUserRouteDetailsId = null;
		
		//Get User
		Query query = JPA.em().createQuery("select s from User s where s.userEmail ='"+userEmailID+"'");
	    User dbUser = (User)query.getSingleResult();
	    Long dbUserId = dbUser.getId();
	    Logger.info("getMapStaticLocationsForaRoute userId from db " + dbUserId);
	    
	    //get the first subscription
	    //TODO Need to change the logic to get the route based on lookup time
	    Query subsriptionQ = JPA.em().createQuery("select s from UserRouteSubscription s where s.user.id=" +dbUserId +" order by id" ).setMaxResults(1);
	    
	    try{
	    	UserRouteSubscription dbUserSubscription = (UserRouteSubscription)subsriptionQ.getSingleResult();
	    	dbUserRouteDetailsId = dbUserSubscription.getRouteDetails().getId();
	    }
	    catch(javax.persistence.NoResultException e){
	    	Logger.info("getMapStaticLocationsForaRoute User is not suscribed to any route " + dbUserId);
	    }
	    
	    
	    Logger.info("getMapStaticLocationsForaRoute dbUserSubId from db " + dbUserRouteDetailsId);
	    
	    if(dbUserRouteDetailsId == null){
	    	renderJSON(gson.toJson("User is not subscribed to any route"));
	    }
	    else{
	    	CompleteRouteDetail routeDetails = CompleteRouteDetail.findById(dbUserRouteDetailsId);
	    	//Ok got the routeDetails now need to update the cta based on Checkins
		    //Get last checkin for this route get the checkin time
	    	
	    	Calendar today = Calendar.getInstance();
	    	today.set(Calendar.HOUR_OF_DAY, 6); // same for minutes and seconds
	    	Date todayDateMorning = today.getTime();
	    	
	    	today.set(Calendar.HOUR_OF_DAY, 18); // same for minutes and seconds
	    	Date todayDateAfternoon = today.getTime();
	    	
	    	/**
	    	 * Query query = JPA.em().createNativeQuery(sql);
		BigInteger totalCheckinsBig =  ((BigInteger) query.getSingleResult()); 
		totalCheckins = totalCheckinsBig.longValue();
	    	 */
	    	
	    	
	    	String sql3 = "select s from UserCheckin s where s.completeroute_id="+ dbUserRouteDetailsId+
	    			" and s.checkinTime between "+todayDateMorning+ " AND "+todayDateAfternoon+" order by s.checkinTime desc" ;
	    	Query query3 = JPA.em().createNativeQuery(sql3);
	    	
	    	/*UserCheckin lastCheckinLoc = UserCheckin.find(sql)
	    			.setParameter("dbUserRouteDetailsId", dbUserRouteDetailsId)
	    			.setParameter("startDate", todayDateMorning)
	    			.setParameter("endDate", todayDateAfternoon)
	    			.first();*/
	    	
	    	renderJSON(gson.toJson(routeDetails));
	    }
	    
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
	
	
	/**
	 * Add a User
	 */

	public static void addUser() {
		Logger.info("addUser" + request);
		User myModel = new GsonBuilder().create().fromJson(new InputStreamReader(request.body), User.class);
		myModel.setUserName(myModel.getUserEmail());
		myModel.setUserPMFKey(myModel.getUserEmail());
		myModel.save();
		myModel.refresh();
		Logger.info("addUser " + myModel.getId());
		renderJSON(gson.toJson(myModel));
	}
	
	public static void addUserRoutes() {
		Logger.info("addUserRoutes" + request);
		Logger.info("addUserRoutes Content Type " + request.contentType);
		
		UserRoutesPost myDtoModel = new GsonBuilder().create().fromJson(new InputStreamReader(request.body), UserRoutesPost.class);
		
		String userEmailIdFromReq = myDtoModel.getUserEmail();
		Logger.info("addUserRoutes userEmailFromReq " + userEmailIdFromReq);
		String[] reqRouteDetailIds = myDtoModel.getRouteDetailIds();
		List<String> idsAsList = Arrays.asList(reqRouteDetailIds);
		
		List<Long> idsAsListLong = new ArrayList<Long>();
		for(String s : idsAsList){
			Long l1 = Long.parseLong(s);
			idsAsListLong.add(l1);
		}
		
		Logger.info("addUserRoutes reqRouteDetailIds " + idsAsListLong);
		
		Query query1 = JPA.em().createQuery("select s from User s where s.userEmail ='" +userEmailIdFromReq+"'").setMaxResults(1);
	    User dbUser = (User)query1.getSingleResult();
		
	   // CompleteRouteDetail dbUserRouteDetails = CompleteRouteDetail.find("");
	    Query query2 = JPA.em().createQuery("select s from CompleteRouteDetail s where s.id IN :idsAsList");
	    query2.setParameter("idsAsList", idsAsListLong);

	    //for outputing response
	    try{
		    List<CompleteRouteDetail> dbRouteDetails = query2.getResultList();
		    for(CompleteRouteDetail newMapping : dbRouteDetails){
		    	UserRouteSubscription myModel = new UserRouteSubscription();
				myModel.setUser(dbUser);
				myModel.setRouteDetails(newMapping);
				myModel.save();
				myModel.refresh();
		    }
		    
		    renderJSON(gson.toJson(myDtoModel));
	    }
	    catch(javax.persistence.PersistenceException e){
	    	
	    	Logger.info("addUserRoutes Exception  " + e);
	    	renderJSON("Error : " + e.getMessage());
	    }
	    
		//myModel.refresh();
		//Logger.info("addUserRoutes " + myModel.getId());
		
	}
	
	
	
}
