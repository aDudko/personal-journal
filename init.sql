CREATE DATABASE IF NOT EXIST personal-journal;

CREATE TABLE IF NOT EXISTS post (
    id          bigint default nextval('post_id_seq'::regclass) not null,
    title       text,
    create_date timestamp(6),
    change_date text,
    tag         text,
    text        text,
    status      text
);

ALTER TABLE IF EXISTS post
    owner to postgres;