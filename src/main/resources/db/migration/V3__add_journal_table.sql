create table journals
(
    id          uuid default uuid_generate_v4() primary key,
    question_id uuid references questions (id) on delete cascade,
    author_id   uuid references authors (id) on delete cascade,
    name        varchar(255) unique
);

create table journal_chapters
(
    id         uuid      default uuid_generate_v4() primary key,
    journal_id uuid references journals (id) on delete cascade,
    name       varchar(255) unique,
    color      varchar(7),
    created_at timestamp default now(),
    updated_at timestamp default now()
);
