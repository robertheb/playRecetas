# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ingredient (
  id                            bigint not null,
  name                          varchar(255),
  description                   varchar(255),
  constraint pk_ingredient primary key (id)
);
create sequence ingredient_seq;

create table recipe (
  id                            bigint not null,
  title                         varchar(255),
  preparation_time              varchar(255),
  description                   varchar(255),
  constraint pk_recipe primary key (id)
);
create sequence recipe_seq;


# --- !Downs

drop table if exists ingredient;
drop sequence if exists ingredient_seq;

drop table if exists recipe;
drop sequence if exists recipe_seq;

