DROP TABLE  IF EXISTS organization;

CREATE TABLE organization (
        id serial primary key,
        inn varchar(12) not null,
        organization_name text not null,
        lastname varchar(255) not null,
        firstname varchar(255) not null,
        patronymic varchar(255) default ''
);