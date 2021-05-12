DELETE
FROM user_roles;
DELETE FROM restaurant;
DELETE FROM dish;
DELETE FROM vote;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.ru', '{noop}password'),
       ('Admin', 'admin@mail.ru', '{noop}password'),
       ('User2', 'user2@mail.ru', '{noop}password');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurant (name)
VALUES ('RestaurantGood'),
       ('RestaurantNormal'),
       ('RestaurantBad');

INSERT INTO dish (name, date, restaurant_id, price)
VALUES ('Lunch1-RestaurantGood', '2021-04-22', 100003, 300),
       ('Lunch2-RestaurantGood', '2021-04-22', 100003, 350),
       ('Lunch3-RestaurantGood', '2021-04-22', 100003, 400),
       ('Lunch1-RestaurantNormal', CURRENT_DATE, 100004, 200),
       ('Lunch2-RestaurantNormal', CURRENT_DATE, 100004, 250),
       ('Lunch1-RestaurantBad', CURRENT_DATE, 100005, 150),
       ('Lunch2-RestaurantBad', CURRENT_DATE, 100005, 100);

INSERT INTO vote (date, user_id, restaurant_id)
VALUES ('2021-04-23', 100000, 100004),
       ('2021-04-23', 100001, 100004),
       ('2021-04-24', 100000, 100003),
       ('2021-04-24', 100001, 100003),
       (now, 100001, 100005),
       (now, 100002, 100005);
