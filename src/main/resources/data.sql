insert into user (ID, EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME)
values (1, 'test1@naver.com', '1111', '010-1111-1111', '2021-02-01 00:11:11.000000', null, 'user1');
insert into user (ID, EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME)
values (2, 'test2@naver.com', '2222', '010-2222-2222', '2021-02-01 00:11:11.000000', null, 'user2');
insert into user (ID, EMAIL, PASSWORD, PHONE, REG_DATE, UPDATE_DATE, USER_NAME)
values (3, 'test3@naver.com', '3333', '010-3333-2222', '2021-02-01 00:11:11.000000', null, 'user3');


insert into notice (ID, CONTENTS, HITS, LIKES, REG_DATE, TITLE, DELETED, USER_ID)
values (1, '내용1', 0, 0, '2021-02-01 00:11:11.000000', '제목1', 0, 1);
insert into notice (ID, CONTENTS, HITS, LIKES, REG_DATE, TITLE, DELETED, USER_ID)
values (2, '내용2', 0, 0, '2021-02-01 00:11:11.000000', '제목2', 0, 1);
insert into notice (ID, CONTENTS, HITS, LIKES, REG_DATE, TITLE, DELETED, USER_ID)
values (3, '내용3', 0, 0, '2021-02-01 00:11:11.000000', '제목3', 0, 1);