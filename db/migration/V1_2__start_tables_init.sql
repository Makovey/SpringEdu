create table USER
(
	id int not null,
	name varchar(255) not null
);

create unique index user_id_uindex
	on user (id);

alter table USER
	add constraint user_pk
		primary key (id);

INSERT INTO test.USER (id, name)
VALUES (3, 'NewUser');

