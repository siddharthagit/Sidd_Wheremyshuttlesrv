
@@@@@@@@@@@@@@@@@@@@
 Play Commands 

 play dependencies
 play install db-1.1.1
@@@@@@@@@@@@@@@@@@@@


Tables:::

UserCheckin
===========
routeid | time | gpslocation| user_email


User
=====
userid(emailid) | notifications (boolean)

RouteDetails
============
routeid | stopid | eta


Stop:
=======
stopid | name | lat | long | desc


UserRoute
==========
id | userId | routeid



Route
======
routeid | route_details | start_time | end_time



///todo
how to populate map




//Configuration
db=mysql://root@localhost/wheremyshuttlesrv_dev

create database wheremyshuttlesrv_dev


!-------------------------------------------- Consuming API ----------------------------------------------------------------!

1) Post userChecking
http://localhost:9000/api/v1/User/checkin

Content-Type : application/json

{
"checkinTime": "Aug 26, 2014 12:10:10 AM",
"checkinLocationLat": 2001,
"checkinLocationLon": 2002,
"routeDetailsId": 1,
"checkinId": 1,
"userId": 1,
"userEmailId": "siddhartha.bhattacharjee@ca.com"
 
}