package controllers;

import com.google.gson.JsonElement;

import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.HttpResponse;

import play.libs.WS;
import play.mvc.*;
import play.mvc.results.Result;

public class MapAPIUtil {

	public static  JsonElement getRealtimeDate(String param1, String param2){
		//https://maps.googleapis.com/maps/api/distancematrix/json?
		//&mode=driving&origins=37.71581,-122.456808&destinations=37.687924,-122.470208
		
		String baseURl2 = "https://maps.googleapis.com/maps/api/distancematrix/json?&mode=driving&" +
				"origins=37.71581,-122.456808&" +
				"destinations=37.687924,-122.470208|37.793816,-122.395682";
		
		String baseURl = "https://maps.googleapis.com/maps/api/distancematrix/json?&mode=driving&" +
				"origins="+param1 +"&" +
				"destinations="+param2+"";
		
		
		HttpResponse res = WS.url(baseURl).get();
		
		String content = res.getString();
		
		JsonElement json = res.getJson();
		//if( 1 ==1)
		//throw new IllegalArgumentException("n must be < 60");

		
		return json;
	
	}
	
}
