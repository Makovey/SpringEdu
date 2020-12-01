create table public.message
(
    id         int4 not null,
    created_at timestamp,
    text       varchar(255),
    user_id    int4,
    primary key (id)
);
create table public.users
(
    id        int4 not null,
    name      varchar(255),
    nick_name varchar(255),
    primary key (id)
);
alter table if exists public.message
    add constraint FKpdrb79dg3bgym7pydlf9k3p1n foreign key (user_id) references public.users;