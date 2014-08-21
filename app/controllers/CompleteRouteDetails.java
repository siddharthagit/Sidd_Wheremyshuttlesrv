package controllers;



import models.CompleteRouteDetail;
import play.mvc.With;


@With(Application.class)
@CRUD.For(CompleteRouteDetail.class)
public class CompleteRouteDetails extends CRUD{

}
