package controllers;



import models.UserCheckin;
import play.mvc.With;


@With(Application.class)
@CRUD.For(UserCheckin.class)
public class UserCheckins extends CRUD{

}
