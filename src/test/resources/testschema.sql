drop table if exists book CASCADE;
drop table if exists review CASCADE;

create table book
(
   id bigint auto_increment,
   author varchar (255),
   description varchar (255),
   title varchar (255),
   primary key (id)
);
create table review
(
   id bigint auto_increment,
   first_name varchar (255),
   surname varchar (255),
   rating integer not null,
   review varchar (255),
   book_id bigint,
   primary key (id),
   foreign key (book_id) references book(id) on delete cascade
);