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

create table recipe_ingredient (
  recipe_id                     bigint not null,
  ingredient_id                 bigint not null,
  constraint pk_recipe_ingredient primary key (recipe_id,ingredient_id)
);

alter table recipe_ingredient add constraint fk_recipe_ingredient_recipe foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;
create index ix_recipe_ingredient_recipe on recipe_ingredient (recipe_id);

alter table recipe_ingredient add constraint fk_recipe_ingredient_ingredient foreign key (ingredient_id) references ingredient (id) on delete restrict on update restrict;
create index ix_recipe_ingredient_ingredient on recipe_ingredient (ingredient_id);


# --- !Downs

alter table recipe_ingredient drop constraint if exists fk_recipe_ingredient_recipe;
drop index if exists ix_recipe_ingredient_recipe;

alter table recipe_ingredient drop constraint if exists fk_recipe_ingredient_ingredient;
drop index if exists ix_recipe_ingredient_ingredient;

drop table if exists ingredient;
drop sequence if exists ingredient_seq;

drop table if exists recipe;
drop sequence if exists recipe_seq;

drop table if exists recipe_ingredient;

