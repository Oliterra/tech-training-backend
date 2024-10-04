create table answers
(
    id               uuid      default uuid_generate_v4() primary key,
    author_id        uuid references authors (id) on delete cascade,
    question_id      uuid references questions (id),
    text             text,
    created_at timestamp default now()
);
