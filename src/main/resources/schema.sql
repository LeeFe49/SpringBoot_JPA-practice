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
    STATUS      INTEGER,
    LOCK_YN     BOOLEAN
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

CREATE TABLE USER_LOGIN_HISTORY
(
    ID         BIGINT auto_increment primary key,
    USER_ID    BIGINT,
    EMAIL      varchar(255),
    USER_NAME  varchar(255),
    LOGIN_DATE TIMESTAMP,
    IP_ADDR    varchar(255)
);

create table BOARD_TYPE
(
    ID          BIGINT auto_increment primary key,
    BOARD_NAME  VARCHAR(255),
    REG_DATE    TIMESTAMP,
    UPDATE_DATE TIMESTAMP
);

-- auto-generated definition
create table BOARD
(
    ID            BIGINT auto_increment primary key,
    CONTENTS      CHARACTER VARYING(255),
    REG_DATE      TIMESTAMP,
    TITLE         CHARACTER VARYING(255),
    BOARD_TYPE_ID BIGINT,
    USER_ID       BIGINT,
    constraint FK_BOARD_BOARD_TYPE_ID foreign key (BOARD_TYPE_ID) references BOARD_TYPE,
    constraint FK_BOARD_USER_ID foreign key (USER_ID) references USER
);

