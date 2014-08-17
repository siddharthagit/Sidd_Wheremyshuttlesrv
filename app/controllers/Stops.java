package controllers;


import models.Stop;
import play.mvc.With;


@With(Application.class)
@CRUD.For(Stop.class)
public class Stops extends CRUD{

}
