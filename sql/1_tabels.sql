

DROP DATABASE IF EXISTS biotrio;

CREATE DATABASE IF NOT EXISTS biotrio;

USE biotrio;


   # CREATE TABLES


CREATE TABLE IF NOT EXISTS movies(
  movie_id int not null auto_increment primary key,
  title varchar(255) not null,
  genre varchar(255) not null,
  rating double,
  age_limit int,
  description varchar(255),
  length_in_minutes int,
  image_name varchar(255),
  trailer_url varchar(255),
  release_year DATETIME not null
);

CREATE TABLE IF NOT EXISTS theaters(
  theater_id int not null auto_increment primary key,
  title varchar(255),
  seats_pr_row int not null,
  number_of_rows int not null 
);

CREATE TABLE IF NOT EXISTS users(
  user_id int not null auto_increment primary key,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  phone_number varchar(20) not null,
  email varchar(255) not null unique,
  password varchar(255) not null,
  role varchar(255) not null,
  active boolean not null
);

CREATE TABLE IF NOT EXISTS movie_plays(
  play_id int not null auto_increment primary key,
  movie_id int not null,
  theater_id int not null,
  play_start DATETIME not null,
  play_end DATETIME not null,
  foreign key(movie_id) REFERENCES movies(movie_id),
  foreign key(theater_id) REFERENCES theaters(theater_id)
);

CREATE TABLE IF NOT EXISTS bookings(
  booking_code varchar(255) not null primary key,
  movie_play_id int not null,
  total_price int,
  foreign key(movie_play_id) REFERENCES movie_plays(play_id)
);

CREATE TABLE IF NOT EXISTS tickets(
  ticket_id int not null auto_increment primary key,
  seat_row int not null,
  seat_nr int not null,
  booking_code varchar(255) not null,
  movie_play_id int not null,
  foreign key(booking_code) REFERENCES bookings(booking_code),
  foreign key(movie_play_id) REFERENCES movie_plays(play_id)
);
