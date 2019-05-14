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

        List<Movie> movieList = generateMovie(rs);

        return movieList;
    }

    private List<Movie> generateMovie(SqlRowSet rs) {
        List<Movie> movieList = new ArrayList<>();

        while(rs.next()) {
            Movie movie = new Movie();

            movie.setId(rs.getInt("movie_id"));
            movie.setTitle(rs.getString("title"));
            movie.setGenre(rs.getString("genre"));
            movie.setRating(rs.getInt("rating"));
            movie.setAgeLimit(rs.getInt("age_limit"));

            movieList.add(movie);
        }

        return movieList;
    }
}
