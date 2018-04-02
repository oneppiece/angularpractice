# DROP DATABASE angular;
# DROP TABLE users;
# DROP TABLE roles;
# DROP TABLE resources;
# DROP TABLE user_role;
# DROP TABLE role_resource;
# DROP TABLE persistant_login;
# #
# # #
# USE angular;
TRUNCATE TABLE users;
TRUNCATE TABLE roles;
TRUNCATE TABLE resources;

TRUNCATE TABLE role_resource;
TRUNCATE TABLE user_role;
TRUNCATE TABLE persistant_login;

CREATE DATABASE IF NOT EXISTS angular;
USE angular;


CREATE TABLE IF NOT EXISTS users (
  id                    INT AUTO_INCREMENT NOT NULL,
  name                  VARCHAR(20) DEFAULT '未设置',
  username              VARCHAR(20) UNIQUE NOT NULL,
  password              VARCHAR(20) DEFAULT 'password',
  roleName              VARCHAR(20) DEFAULT 'ROLE_USER',
  accountNonExpired     BOOLEAN     DEFAULT TRUE,
  accountNonLocked      BOOLEAN     DEFAULT TRUE,
  credentialsNonExpired BOOLEAN     DEFAULT TRUE,
  enabled               BOOLEAN     DEFAULT TRUE,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 2;

CREATE TABLE IF NOT EXISTS roles (
  id   INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(20) DEFAULT '未设置',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 2;

CREATE TABLE IF NOT EXISTS resources (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(20) DEFAULT '未设置',
  description VARCHAR(20) DEFAULT '描述',
  url         VARCHAR(20) DEFAULT '描述',
  pid         INT,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  AUTO_INCREMENT = 2;


CREATE TABLE IF NOT EXISTS user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (role_id) REFERENCES roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS role_resource (
  role_id     INT NOT NULL,
  resource_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (resource_id) REFERENCES resources (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) NOT NULL,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL,
  PRIMARY KEY (series)
);