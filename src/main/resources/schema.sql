drop table if exists USER;
drop table if exists notice;

create table USER
(
    ID          BIGINT auto_increment primary key,
    EMAIL       varchar(255),
    USER_NAME   varchar(255),
    PASSWORD    VARCHAR(255),
    PHONE       VARCHAR(255),
    REG_DATE    TIMESTAMP,
    UPDATE_DATE TIMESTAMP,
    STATUS      INTEGER
);

CREATE TABLE NOTICE
(
    ID           BIGINT auto_increment primary key,
    TITLE        VARCHAR(255),
    CONTENTS     VARCHAR(255),

    HITS         INTEGER,
    LIKES        INTEGER,

    REG_DATE     TIMESTAMP,
    UPDATE_DATE  TIMESTAMP,
    DELETED_DATE TIMESTAMP,
    DELETED      BOOLEAN,

    USER_ID      BIGINT,
    constraint FK_NOTICE_USER_ID foreign key (USER_ID) references USER (ID)
);