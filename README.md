# Voting system for restaurant
(Graduation project Topjava)

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

## Curl samples 
## Users with Admin role:

#### get All Users
`curl -s http://localhost:8080/rest/admin/users --user admin@mail.ru:password`

#### get User 100001
`curl -s http://localhost:8080/rest/admin/users/100001 --user admin@mail.ru:password`

#### get User by email user@mail.ru
`curl -s http://localhost:8080/rest/admin/users/by?email=user@mail.ru --user admin@mail.ru:password`

#### create User
`curl -s -X POST -d '{"name":"NewUser11", "email":"user11@mail.ru", "password":"password", "roles":["ROLE_USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@mail.ru:password`

#### delete User 100014
`curl -s -X DELETE http://localhost:8080/rest/admin/users/100014 --user admin@mail.ru:password`

#### update User 100000
`curl -s -X PUT -d '{"name":"NewUser2", "email":"user1@mail.ru", "password":"password2"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100000 --user admin@mail.ru:password`

## New User:

#### create User
`curl -s -X POST -d '{"name":"NewUser11", "email":"user11@mail.ru", "password":"password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/profile/register`

## Users with User role:

#### get profile
`curl -s http://localhost:8080/rest/profile --user user@mail.ru:password`

#### update profile
`curl -s -X PUT -d '{"name":"NewUser2", "email":"user1@mail.ru", "password":"password2"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/profile --user user@mail.ru:password`

#### delete profile
`curl -s -X DELETE http://localhost:8080/rest/profile --user user11@mail.ru:password`

## Restaurant with Admin role:

#### get Restaurants
`curl -s http://localhost:8080/rest/admin/restaurants --user admin@mail.ru:password`

#### create Restaurant
`curl -s -X POST -d '{"name":"New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@mail.ru:password`

#### update Restaurant 100004
`curl -s -X PUT -d '{"name":"Restaurant2_update"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/100004 --user admin@mail.ru:password`

#### delete Restaurant 100015
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100015 --user admin@mail.ru:password`

## Restaurant with User role:

#### get all Restaurants for today
`curl -s http://localhost:8080/rest/restaurants --user user@mail.ru:password`

#### get Restaurant 100003 for today
`curl -s http://localhost:8080/rest/restaurants/100003 --user user@mail.ru:password`

#### get Restaurant 100002 with dish for date 2019-11-23
`curl -s http://localhost:8080/rest/restaurants/100002?date=2019-11-23 --user user@mail.ru:password`

#### get All Restaurants with dish for date 2019-11-23
`curl -s http://localhost:8080/rest/restaurants?date=2019-11-23 --user user@mail.ru:password`

## Dish with Admin role:

#### get All Dish for restaurant 100003
`curl -s http://localhost:8080/rest/admin/restaurants/100003/dishes --user admin@mail.ru:password`

#### get Dish 100008
`curl -s http://localhost:8080/rest/admin/restaurants/100003/dishes/100008 --user admin@mail.ru:password`

#### create Dish
`curl -s -X POST -d '{"name":"New dish","price":100,"date":"2019-12-08"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100003/dishes --user admin@mail.ru:password`

#### update Dish 100005
`curl -s -X PUT -d '{"name":"Updated_DISH1","price":200,"date":"2019-12-08"}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/100003/dishes/100005 --user admin@mail.ru:password`

#### delete Dish 100008
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100003/dishes/100008 --user admin@mail.ru:password`

## Vote with Admin role:

#### get all Votes for today
`curl -s http://localhost:8080/rest/admin/users/votes/ --user admin@mail.ru:password`

## Vote with User role:

#### Vote for Restaurant 100004
`curl -s -X POST -d '{"restaurantId":"100004"}' -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/rest/votes --user user@mail.ru:password`

#### Change Vote for Restaurant 100004
`curl -s -X PUT -d '{"restaurantId":"100004"}' -H "Content-Type:application/json;charset=UTF-8" http://localhost:8080/rest/votes/100017 --user user@mail.ru:password`

#### get Vote for today
`curl -s http://localhost:8080/rest/votes/ --user user@mail.ru:password`

#### filter Votes
`curl -s "http://localhost:8080/rest/votes/filter?startDate=2019-10-01&endDate=2019-12-29" --user user@mail.ru:password`
