CREATE TABLE ROLE (
  id SERIAL PRIMARY KEY,
  name varchar(255) not null unique
);

CREATE TABLE USERS (
  id SERIAL PRIMARY KEY,
  username varchar(255) not null unique,
  password varchar(255) not null,
  email varchar(255) not null unique,
  last_login timestamp
);

CREATE TABLE USER_ROLES (
  id_user bigint not null,
  id_role bigint not null
);

