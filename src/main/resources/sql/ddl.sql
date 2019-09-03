-- CREATE DATABASE dealer_stat_repository ENCODING ='UTF8';
-- CREATE SCHEMA dealer_stat;

CREATE TABLE dealer_stat.user
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64)  NOT NULL,
    last_name  VARCHAR(64)  NOT NULL,
    password   VARCHAR(64)  NOT NULL,
    email      VARCHAR(64)  NOT NULL UNIQUE,
    created_at TIMESTAMP(0) NOT NULL,
    role       VARCHAR(16)  NOT NULL
);

CREATE TABLE dealer_stat.game
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE dealer_stat.user_game
(
    user_id BIGINT REFERENCES dealer_stat.user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    game_id BIGINT REFERENCES dealer_stat.game (id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, game_id)
);

CREATE TABLE dealer_stat.game_object
(
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(64)  NOT NULL,
    text       TEXT,
    status     VARCHAR(16)  NOT NULL,
    author_id  BIGINT REFERENCES dealer_stat.user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMP(0) NOT NULL,
    updated_at TIMESTAMP(0) NOT NULL,
    game_id    BIGINT REFERENCES dealer_stat.game (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dealer_stat.comment
(
    id         BIGSERIAL PRIMARY KEY,
    message    TEXT         NOT NULL,
    rating     INTEGER      NOT NULL,
    post_id    BIGINT REFERENCES dealer_stat.game_object (id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id  BIGINT REFERENCES dealer_stat.user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMP(0) NOT NULL,
    approved   BOOLEAN      NOT NULL
);
