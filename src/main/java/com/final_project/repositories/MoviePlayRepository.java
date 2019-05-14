package com.final_project.repositories;

import com.final_project.entities.MoviePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MoviePlayRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MoviePlay> getMoviePlays() {
        String sqlQuery = "SELECT * FROM movie_plays";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<MoviePlay> moviePlayList = generateMoviePlays(rs);

        return moviePlayList;
    }

    public List<MoviePlay> getMoviePlaysByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id =" + movieId;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<MoviePlay> moviePlayList = generateMoviePlays(rs);

        return  moviePlayList;
    }

    public MoviePlay getMoviePlayById(int id) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE play_id =" + id;

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        MoviePlay moviePlay = generateMoviePlay(rs);

        return moviePlay;

    }

    public int addMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "INSERT INTO movie_plays(movie_id, theater_id, play_start) VALUES(?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, moviePlay.getMovieId(), moviePlay.getTheaterId(), moviePlay.getPlayStart());
    }

    public int deleteMoviePlay(int id) {
        String sqlQuery = "DELETE FROM movie_plays WHERE play_id=" + id;

        return jdbcTemplate.update(sqlQuery);
    }

    private MoviePlay generateMoviePlay(SqlRowSet rs) {
        MoviePlay moviePlay = new MoviePlay();

        while(rs.next()) {
            moviePlay.setId(rs.getInt("play_id"));
            moviePlay.setMovieId(rs.getInt("movie_id"));
            moviePlay.setTheaterId(rs.getInt("theater_id"));

            Timestamp ts = rs.getTimestamp("play_start");
            moviePlay.setPlayStart(ts.toLocalDateTime());
        }

        return moviePlay;
    }

    private List<MoviePlay> generateMoviePlays(SqlRowSet rs) {
        List<MoviePlay> moviePlayList = new ArrayList<>();

        while(rs.next()) {
            MoviePlay moviePlay = new MoviePlay();

            moviePlay.setId(rs.getInt("play_id"));
            moviePlay.setMovieId(rs.getInt("movie_id"));
            moviePlay.setTheaterId(rs.getInt("theater_id"));

            Timestamp ts = rs.getTimestamp("play_start");
            moviePlay.setPlayStart(ts.toLocalDateTime());

            moviePlayList.add(moviePlay);
        }

        return moviePlayList;
    }
}
