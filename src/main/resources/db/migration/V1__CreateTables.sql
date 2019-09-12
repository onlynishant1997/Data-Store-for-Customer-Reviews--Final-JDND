create table product(id integer auto_increment, category varchar(255) not null, description varchar(255) not null, name varchar(255) not null, primary key (id));
create table review (id integer auto_increment, review_text varchar(255) not null, title varchar(255) not null, product_id integer not null, primary key (id));
create table comment (id integer auto_increment, comment_text varchar(255) not null, heading varchar(255) not null, review_id integer not null, primary key (id));
