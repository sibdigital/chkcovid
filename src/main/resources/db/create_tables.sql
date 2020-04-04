DROP TABLE  IF EXISTS organisation;

CREATE TABLE organisation (
        itn varchar(12) not null,
        organization_name varchar(255) not null,
        lastname varchar(255) not null,
        firstname varchar(255) not null,
        patronymic varchar(255),
        primary key (itn, firstname, lastname)
);