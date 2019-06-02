/*
# 'BookingRepository'

	SELECT * FROM bookings

	SELECT * FROM bookings WHERE booking_code = ?

	SELECT * FROM bookings WHERE movie_play_id = ?

	INSERT INTO bookings(movie_play_id, total_price, booking_code) VALUES(?, ?, ?)

	DELETE FROM bookings WHERE booking_code = ?

# 'MoviePlayRepository'

	SELECT * FROM movie_plays ORDER BY play_start

	SELECT * FROM movie_plays ORDER BY play_start LIMIT ?

	SELECT * FROM movie_plays WHERE movie_id = ? ORDER BY play_start

	SELECT * FROM movie_plays WHERE play_id = ?

	SELECT * FROM movie_plays WHERE theater_id = ?

	INSERT INTO movie_plays(movie_id, theater_id, play_start, play_end) VALUES(?, ?, ?, ?)

	DELETE FROM movie_plays WHERE play_id = ?

	UPDATE movie_plays SET movie_id = ?, theater_id = ?, play_start = ?, play_end = ? WHERE play_id = ?

# 'MovieRepository'

	SELECT * FROM movies

	SELECT * FROM movies ORDER BY rating LIMIT ?

	SELECT * FROM movies WHERE movie_id=

	SELECT * FROM movies WHERE title LIKE ?

	INSERT INTO movies(title, genre, rating, release_year, length_in_minutes, age_limit, image_name, trailer_url, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)

	UPDATE movies SET title = ?, genre = ?, rating = ?, release_year = ?, length_in_minutes = ?, age_limit = ?, image_name = ?, trailer_url = ?, description = ? WHERE movie_id = ?

	DELETE FROM movies WHERE movie_id = ?

# 'TheaterRepository'

	INSERT INTO theaters(title, seats_pr_row, number_of_rows) VALUES (?,?,?)

	SELECT * FROM theaters

	SELECT * FROM theaters WHERE theater_id =

	DELETE FROM theaters WHERE theater_id = ?

	UPDATE theaters SET title = ?, seats_pr_row = ?, number_of_rows = ? WHERE theater_id = ?

# 'TicketRepository'

	SELECT * FROM tickets WHERE booking_code = ?

	SELECT * FROM tickets WHERE movie_play_id = ?

	INSERT INTO tickets(booking_code, seat_nr, seat_row, movie_play_id) VALUES(?, ?, ?, ?)

	DELETE FROM tickets WHERE ticket_id = ?

# 'UserRepository'

	select * from users where email = ?

	INSERT INTO users(first_name, last_name, email, password, phone_number, role, active) VALUES(?, ?, ?, ?, ?, ?, ?)

*/