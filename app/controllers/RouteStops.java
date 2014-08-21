package controllers;




import models.RouteStop;
import play.mvc.With;


@With(Application.class)
@CRUD.For(RouteStop.class)
public class RouteStops extends CRUD{

}
