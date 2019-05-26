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

    public Movie findMovieById(int id) {
        String sqlQuery = "SELECT * FROM movies WHERE movie_id=" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        Movie movie = generateMovie(rs);

        return movie;
    }

    public void addMovie(Movie movie) {
        String sqlQuery = "INSERT INTO movies(title, genre, rating, release_year, length_in_minutes, age_limit, image_name) VALUES(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getAgeLimit(), movie.getImageName());
    }

    public void editMovie(Movie movie) {
        String sqlQuery = "UPDATE movies SET title = ?, genre = ?, rating = ?, release_year = ?, length_in_minutes = ?, age_limit = ?, image_name = ? WHERE movie_id = " + movie.getId();

        jdbcTemplate.update(sqlQuery, movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getAgeLimit(), movie.getImageName());
    }

    public void deleteMovie(int id) {
        String sqlQuery = "DELETE FROM movies WHERE movie_id=" + id;

        jdbcTemplate.update(sqlQuery);
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

        }

        return movie;
    }
}
