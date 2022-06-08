drop table if exists book CASCADE;
drop table if exists review CASCADE;
create table book
(
   id bigint generated by default as identity,
   author varchar (255),
   description varchar (255),
   title varchar (255),
   primary key (id)
);
create table review
(
   id bigint generated by default as identity,
   first_name varchar (255),
   rating integer not null,
   review varchar (255),
   surname varchar (255),
   book_id bigint,
   primary key (id)
);
alter table review add constraint FK70yrt09r4r54tcgkrwbeqenbs foreign key (book_id) references book on delete cascade;