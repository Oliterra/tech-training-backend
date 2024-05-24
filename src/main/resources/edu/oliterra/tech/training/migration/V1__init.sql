--date: 2024-05-20
--author: nadezhda.tarasova

create extension if not exists "uuid-ossp";

drop table if exists categories cascade;
drop table if exists authors cascade;
drop table if exists questions cascade;
drop table if exists tags cascade;
drop table if exists questions_tags cascade;
drop table if exists comments cascade;

create table categories
(
    id          uuid      default uuid_generate_v4() primary key,
    name        varchar(255) not null unique,
    description text,
    created_at  timestamp default current_timestamp
);

create table authors
(
    id    uuid default uuid_generate_v4() primary key,
    login varchar(100)
);

create table questions
(
    id          uuid      default uuid_generate_v4() primary key,
    title       varchar(255) not null unique,
    description text,
    category_id uuid,
    author_id   uuid,
    difficulty  varchar(20),
    created_at  timestamp default current_timestamp,
    foreign key (category_id) references categories (id),
    foreign key (author_id) references authors (id)
);

create table tags
(
    id   uuid default uuid_generate_v4() primary key,
    name varchar(100)
);

create table questions_tags
(
    tag_id      uuid not null references tags (id) on delete cascade,
    question_id uuid not null references questions (id) on delete cascade,
    primary key (tag_id, question_id)
);

create table comments
(
    id          uuid default uuid_generate_v4() primary key,
    question_id uuid references questions (id) on delete cascade,
    author_id   uuid references authors (id) on delete cascade,
    text        text
);
