CREATE TABLE user_credentials (
  id BIGSERIAL,
  user_name VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE role (
  id BIGSERIAL,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    UNIQUE (user_id, role_id)
);