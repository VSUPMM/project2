create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table "user"
(
    course     integer,
    user_id    bigint not null
        constraint "User_pkey"
            primary key,
    login      varchar(80),
    passward   varchar(80),
    name       varchar(80),
    patronymic varchar(80),
    surname    varchar(80),
    user_group varchar(10)
);

alter table "user"
    owner to postgres;

create table test
(
    test_id bigint not null
        constraint test_pkey
            primary key,
    name    varchar(255)
);

alter table test
    owner to postgres;

create table test_task
(
    value            integer,
    question         varchar(140),
    task_id          bigint not null
        constraint pk_task2
            primary key,
    possible_answers text[],
    test_id          bigint
        constraint "testFk"
            references test,
    right_answers    text[]
);

alter table task
    owner to postgres;

create index "fki_testFk"
    on task (test_id);

create table test_result
(
    fk_test bigint
        constraint fk_test_key
            references test,
    fk_user bigint not null
        constraint "TestResult_fk_user_fkey"
            references "user"
);

alter table test_result
    owner to postgres;

create index fki_test_key
    on test_result (fk_test);

create table practical_task
(
    task_id     bigint not null
        constraint practical_task_pkey
            primary key,
    question    varchar(255),
    right_query text,
    value       integer,
    test_id     bigint
        constraint fk_test
            references test
);

alter table practical_task
    owner to postgres;

create table practical_answer
(
    answer_id  bigint not null
        constraint pk
            primary key,
    user_query text,
    correct    boolean,
    task_id    bigint
        constraint practical_answer_practical_task_task_id_fk
            references practical_task
);

alter table practical_answer
    owner to postgres;

create table test_answer
(
    answer_id bigint not null
        constraint pk_answer
            primary key,
    answer    text[],
    correct   boolean,
    task_id   bigint
        constraint test_answer_test_task_task_id_fk
            references task
);

alter table answer_variant
    owner to postgres;

