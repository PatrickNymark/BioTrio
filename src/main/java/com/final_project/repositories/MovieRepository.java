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

    public List<Movie> getAllMovies() {
        String sqlQuery = "SELECT * FROM movies";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<Movie> movieList = generateMovies(rs);

        return movieList;
    }

    public Movie getMovieById(int id) {
        String sqlQuery = "SELECT * FROM movies WHERE movie_id=" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        Movie movie = generateMovie(rs);

        return movie;
    }

    public void addMovie(Movie movie) {
        String sqlQuery = "INSERT INTO movies(title, genre, rating, release_year, age_limit) VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlQuery, movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getReleaseYear(), movie.getAgeLimit());
    }

    public void editMovie(Movie movie) {
        String sqlQuery = "UPDATE movies SET " +
                "title =" + movie.getTitle() +
                "genre =" + movie.getGenre() +
                "rating =" + movie.getRating() +
                "release_year =" + movie.getReleaseYear() +
                "age_limit =" + movie.getAgeLimit();

        jdbcTemplate.update(sqlQuery);
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
            movie.setRating(rs.getDouble("rating"));
            movie.setAgeLimit(rs.getInt("age_limit"));

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
            movie.setRating(rs.getDouble("rating"));
            movie.setAgeLimit(rs.getInt("age_limit"));
        }

        return movie;
    }
}
