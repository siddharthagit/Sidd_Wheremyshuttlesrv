package controllers;


import models.User;
import play.mvc.With;


@With(Application.class)
@CRUD.For(User.class)
public class Users extends CRUD{

}
