



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