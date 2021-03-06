package com.final_project.repositories;

import com.final_project.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Find all movies
     *
     * @return List(Movie)
     */
    public List<Movie> findAllMovies() {
        String sqlQuery = "SELECT * FROM movies";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<Movie> movieList = generateMovies(rs);

        return movieList;
    }

    /**
     * Find movies with a limit (hardcoded for now - not optimal)
     *
     * @return List(Movie)
     */
    public List<Movie> findTopMovies() {
        int limit = 3;
        String sqlQuery = "SELECT * FROM movies ORDER BY rating LIMIT ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, limit);

        return generateMovies(rs);
    }

    /**
     * Find movie by id
     *
     * @param id
     * @return Movie
     */
    public Movie findMovieById(int id) {
        String sqlQuery = "SELECT * FROM movies WHERE movie_id=" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        Movie movie = generateMovie(rs);

        return movie;
    }

    /**
     * Find movies similar to title
     *
     * @param title
     * @return List(Movie)
     */
    public List<Movie> searchMoviesByTitle(String title) {
        String sqlQuery = "SELECT * FROM movies WHERE title LIKE ?";

        String likeQuery = "%" + title + "%";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, likeQuery);

        return generateMovies(rs);
    }

    /**
     * Saves movie
     *
     * @param movie
     */
    public void addMovie(Movie movie) {
        String sqlQuery = "INSERT INTO movies(title, genre, rating, release_year, length_in_minutes, age_limit, image_name, trailer_url, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, movie.getTitle());
                ps.setString(2, movie.getGenre());
                ps.setDouble(3, movie.getRating());
                ps.setDate(4, Date.valueOf(movie.getReleaseYear()));
                ps.setInt(5, movie.getLengthInMinutes());
                ps.setInt(6, movie.getAgeLimit());
                ps.setString(7, movie.getImageName());
                ps.setString(8, movie.getTrailerUrl());
                ps.setString(9, movie.getDescription());

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    /**
     * Updates movie
     *
     * @param movie
     */
    public void editMovie(Movie movie) {
        String sqlQuery = "UPDATE movies SET title = ?, genre = ?, rating = ?, release_year = ?, length_in_minutes = ?, age_limit = ?, image_name = ?, trailer_url = ?, description = ? WHERE movie_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setString(1, movie.getTitle());
                ps.setString(2, movie.getGenre());
                ps.setDouble(3, movie.getRating());
                ps.setDate(4, Date.valueOf(movie.getReleaseYear()));
                ps.setInt(5, movie.getLengthInMinutes());
                ps.setInt(6, movie.getAgeLimit());
                ps.setString(7, movie.getImageName());
                ps.setString(8, movie.getTrailerUrl());
                ps.setString(9, movie.getDescription());
                ps.setInt(10, movie.getId());

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    /**
     * Deletes movie
     *
     * @param id
     */
    public void deleteMovie(int id) {
        String sqlQuery = "DELETE FROM movies WHERE movie_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, id);

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    /**
     * Generates movies from database
     *
     * @param rs
     * @return List(Movie)
     */
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

    /**
     * Generates movie from database
     *
     * @param rs
     * @return Movie
     */
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
