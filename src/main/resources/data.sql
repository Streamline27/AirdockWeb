INSERT INTO TASK
VALUES('1', 'First Task', 'Some description', null);

INSERT INTO TASK
VALUES('2', 'Second Task', 'Some description', null);

/******
    Creating users
 */

INSERT INTO USER
VALUES('1', 'Vladislav', 'USER');

INSERT INTO CREDENTIALS
VALUES('1', 'USER', '123', '1');


INSERT INTO USER
VALUES('2', 'Denis', 'ADMIN');

INSERT INTO CREDENTIALS
VALUES('2', 'ADMIN', '123', '2');