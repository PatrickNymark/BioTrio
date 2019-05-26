package com.final_project.repositories;

import com.final_project.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> findAllMovies() {
        String sqlQuery = "SELECT * FROM movies";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<Movie> movieList = generateMovies(rs);

        return movieList;
    }

    public List<Movie> findTop3Movies() {
        int limit = 3;
        String sqlQuery = "SELECT * FROM movies ORDER BY rating LIMIT ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, limit);

        return generateMovies(rs);
    }

    public Movie findMovieById(int id) {
        String sqlQuery = "SELECT * FROM movies WHERE movie_id=" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        Movie movie = generateMovie(rs);

        return movie;
    }

    public List<Movie> searchMoviesByTitle(String title) {
        String sqlQuery = "SELECT * FROM movies WHERE title LIKE ?";

        String likeQuery = "%" + title + "%";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, likeQuery);

        return generateMovies(rs);
    }

    public void addMovie(Movie movie) {
        String sqlQuery = "INSERT INTO movies(title, genre, rating, release_year, length_in_minutes, age_limit, image_name, trailer_url, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getAgeLimit(), movie.getImageName(), movie.getTrailerUrl(), movie.getDescription());
    }

    public void editMovie(Movie movie) {
        String sqlQuery = "UPDATE movies SET title = ?, genre = ?, rating = ?, release_year = ?, length_in_minutes = ?, age_limit = ?, image_name = ?, trailer_url = ?, description = ? WHERE movie_id = ?";

        jdbcTemplate.update(sqlQuery, movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getAgeLimit(), movie.getImageName(), movie.getTrailerUrl(), movie.getDescription(), movie.getId());
    }

    public void deleteMovie(int id) {
        String sqlQuery = "DELETE FROM movies WHERE movie_id = ?";

        jdbcTemplate.update(sqlQuery, id);
    }

    private List<Movie> generateMovies(SqlRowSet rs) {
        List<Movie> movieList = new ArrayList<>();

        while(rs.next()) {
            Movie movie = new Movie();

            movie.setId(rs.getInt("movie_id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setReleaseYear(rs.getDate("release_year").toLocalDate());
            movie.setRating(rs.getDouble("rating"));
            movie.setAgeLimit(rs.getInt("age_limit"));
            movie.setImageName(rs.getString("image_name"));
            movie.setLengthInMinutes(rs.getInt("length_in_minutes"));
            movie.setTrailerUrl(rs.getString("trailer_url"));
            movie.setDescription(rs.getString("description"));

            movieList.add(movie);
        }

        return movieList;
    }

    private Movie generateMovie(SqlRowSet rs) {
        Movie movie = new Movie();

        while(rs.next()) {
            movie.setId(rs.getInt("movie_id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setReleaseYear(rs.getDate("release_year").toLocalDate());
            movie.setRating(rs.getDouble("rating"));
            movie.setAgeLimit(rs.getInt("age_limit"));
            movie.setImageName(rs.getString("image_name"));
            movie.setLengthInMinutes(rs.getInt("length_in_minutes"));
            movie.setTrailerUrl(rs.getString("trailer_url"));
            movie.setDescription(rs.getString("description"));

        }

        return movie;
    }
}
