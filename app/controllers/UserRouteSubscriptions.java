package controllers;



import models.UserRouteSubscription;
import play.mvc.With;


@With(Application.class)
@CRUD.For(UserRouteSubscription.class)
public class UserRouteSubscriptions extends CRUD{

}
