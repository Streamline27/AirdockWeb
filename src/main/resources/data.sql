-- Work orders --
INSERT INTO WORK_ORDER(TITLE, DESCRIPTION)
VALUES ('Boeing-747', 'Brief description');

INSERT INTO WORK_ORDER(TITLE, DESCRIPTION)
VALUES ('20 Memphis Belle', 'Brief description');

-- Users --
INSERT INTO USER
VALUES('1', 'Vladislav', 'WORKER');

INSERT INTO CREDENTIALS
VALUES('1', 'USER', '123', '1');


INSERT INTO USER
VALUES('2', 'Denis', 'ADMIN');

INSERT INTO CREDENTIALS
VALUES('2', 'ADMIN', '123', '2');


-- Tasks --
INSERT INTO TASK(ID, WORK_ORDER_ID, USER_ID, TITLE, END_DATE, START_DATE, CREATION_DATE, STATUS, DESCRIPTION)
VALUES('1', '1', '1','Create beautifull description page', '2018-05-24', '2018-05-28', '2018-05-24',  'TODO', '— Но, милая княжна, — кротко и убедительно говорила Анна Михайловна, заступая дорогу от спальни и не пуская княжну, — не будет ли это слишком тяжело для бедного дядюшки в такие минуты, когда ему нужен отдых? В такие минуты разговор о мирском, когда его душа уже приготовлена...');

INSERT INTO TASK(ID, WORK_ORDER_ID, USER_ID, TITLE, END_DATE, START_DATE, CREATION_DATE, STATUS, DESCRIPTION)
VALUES('2', '1', '1', 'Create request screen', '2018-05-24', '2018-05-28', '2018-05-24',  'TODO', '— Я знаю, милая, добрая княжна, — сказала Анна Михайловна, хватаясь рукой за портфель и так крепко, что видно было, она не скоро его пустит. — Милая княжна, я вас прошу, я вас умоляю, пожалейте его. Je vous en conjure...[203]');

INSERT INTO TASK(ID, WORK_ORDER_ID, USER_ID, TITLE, END_DATE, START_DATE, CREATION_DATE, STATUS, DESCRIPTION)
VALUES('3', '2', '1','Add history page', '2018-05-24', '2018-05-28', '2018-05-24',  'TODO', '— Я знаю, милая, добрая княжна, — сказала Анна Михайловна, хватаясь рукой за портфель и так крепко, что видно было, она не скоро его пустит. — Милая княжна, я вас прошу, я вас умоляю, пожалейте его. Je vous en conjure...[203]');

INSERT INTO TASK(ID, WORK_ORDER_ID, USER_ID, TITLE, END_DATE, START_DATE, CREATION_DATE, STATUS, DESCRIPTION)
VALUES('4', '2', '1','Change email for user', '2018-05-24', '2018-05-28', '2018-05-24',  'TODO', '— Я знаю, милая, добрая княжна, — сказала Анна Михайловна, хватаясь рукой за портфель и так крепко, что видно было, она не скоро его пустит. — Милая княжна, я вас прошу, я вас умоляю, пожалейте его. Je vous en conjure...[203]');

INSERT INTO TASK(ID, WORK_ORDER_ID, USER_ID, TITLE, END_DATE, START_DATE, CREATION_DATE, STATUS, DESCRIPTION)
VALUES('5', '1', '1', 'Write bachelors thesis', '2018-05-24', '2018-05-28', '2018-05-24', 'TODO', '— Я знаю, милая, добрая княжна, — сказала Анна Михайловна, хватаясь рукой за портфель и так крепко, что видно было, она не скоро его пустит. — Милая княжна, я вас прошу, я вас умоляю, пожалейте его. Je vous en conjure...[203]');

-- Requests --
INSERT INTO REQUEST(ID, AUTHOR, TITLE, DESCRIPTION, CREATION_DATE, STATUS)
VALUES('1', '1', 'Make notifications', 'Please notify us, when something is wrong with the application, otherwise, we can not identify the responsible for the delay', '2018-05-28', 'PENDING');

INSERT INTO REQUEST(ID, AUTHOR, TITLE, DESCRIPTION, CREATION_DATE, STATUS)
VALUES('2', '1', 'Hurry Up', 'Bach defense is upon us, we must brace our selves for the incoming storm and increase our production speed', '2018-05-28', 'PENDING');

/******
    Creating users
 */

+