

	#  INSERT INTO TABLES

USE biotrio;

INSERT INTO users(first_name, last_name, phone_number, email, role, password, active) 
	VALUES('test', 'test', '00', 'test@test.com', "ROLE_STAFF", "$2a$10$hFwBgHLZEstoysuL2TG/o.jTmB2J2h6tMWU3gXcpEAMnGaDPnKvwa", true);

INSERT INTO movies (title, genre, rating, age_limit, description, length_in_minutes, image_name, trailer_url, release_year)
	VALUES ('Black Panther', 'Action', 7.3, 13.0, "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country's past.", 134, 'black-panther.jpg','https://www.youtube.com/embed/xjDjIWPwcPU', '2018-02-16 00:00:00'),
			('A Quiet Place', 'Horror', 7.6, 13.0, "In a post-apocalyptic world, a family is forced to live in silence while hiding from monsters with ultra-sensitive hearing.", 90, 'a-quiet-place.jpg','https://www.youtube.com/embed/WR7cc5t7tv8', '2018-04-06 00:00:00'),
            ('Captain Marvel', 'Action', 7.1, 13.0, "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.", 123, 'captain-marvel.png','https://www.youtube.com/embed/0LHxvxdRnYc', '2019-03-08 00:00:00'),
			('Get Out', 'Thriller', 7.7, 17.0, "A young African-American visits his white girlfriend's parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.", 104, 'get-out.jpg', 'https://www.youtube.com/embed/DzfpyUB60YY', '2017-02-24 00:00:00'),
            ('Avengers: Endgame', 'Action', 8.8, 13.0, "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.", 181, 'avengers-endgame.jpg','https://www.youtube.com/embed/TcMBFSGVi1c', '2019-04-26 00:00:00'),
            ('Bird Box', 'Horror', 6.6, 17.0, "Five years after an ominous unseen presence drives most of society to suicide, a mother and her two children make a desperate bid to reach safety.", 124, 'bird-box.jpg','https://www.youtube.com/embed/o2AsIXSh2xo', '2018-12-21 00:00:00'),
            ('The Avengers', 'Fantasy', 8.1, 13.0, "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.", 143, 'the-avengers.jpg','https://www.youtube.com/embed/eOrNdBpGMv8', '2012-05-04 00:00:00'),
			('Us', 'Horror', 7.2, 17.0, "A family's serene beach vacation turns to chaos when their doppelgängers appear and begin to terrorize them.", 116, 'us.jpg','https://www.youtube.com/embed/hNCmb-4oXJA', '2019-03-22 00:00:00'),
            ('Avengers: Infinity War', 'Adventure', 8.5, 13.0, "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.", 149, 'avengers-infinity.jpg','https://www.youtube.com/embed/6ZfuNTqbHE8', '2018-04-27 00:00:00'),
            ('Hush', 'Thriller', 6.6, 17.0, "A deaf and mute writer who retreated into the woods to live a solitary life must fight for her life in silence when a masked killer appears at her window.", 82, 'hush.jpg','https://www.youtube.com/embed/Q_P8WCbhC6s', '2016-04-08 00:00:00');			

INSERT INTO theaters (title, seats_pr_row, number_of_rows)
	VALUES ('Blue', 20, 14),
			('Red', 12, 8),
            ('Orange', 6, 8);
   
INSERT INTO movie_plays (movie_id, theater_id, play_start, play_end)
	VALUES (1, 1, '2019-06-03 18:00:00', '2019-06-03 20:44:00'),
		  (2, 2, '2019-06-05 21:00:00', '2019-06-05 23:00:00'),
          (3, 3, '2019-06-07 19:30:00', '2019-06-07 22:03:00'),
          (4, 1, '2019-06-09 18:00:00', '2019-06-09 19:14:00'),
          (5, 2, '2019-06-11 18:30:00', '2019-06-11 22:01:00'),
          (6, 3, '2019-06-13 20:30:00', '2019-06-13 23:04:00'),
          (7, 1, '2019-06-15 22:00:00', '2019-06-16 01:59:00'),
          (8, 2, '2019-06-17 18:00:00', '2019-06-17 20:26:00'),
          (9, 3, '2019-06-19 21:00:00', '2019-06-19 23:53:00'),
          (10, 1, '2019-06-21 19:30:00', '2019-06-21 21:22:00');

