package com.final_project.repositories;

import com.final_project.entities.MoviePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MoviePlayRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MoviePlay> findAllMoviePlays() {
        String sqlQuery = "SELECT * FROM movie_plays ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<MoviePlay> moviePlayList = generateMoviePlays(rs);

        return moviePlayList;
    }

    public List<MoviePlay> findNext3MoviePlays() {
        int limit = 3;

        String sqlQuery = "SELECT * FROM movie_plays ORDER BY play_start LIMIT ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, limit);

        return generateMoviePlays(rs);
    }

    public List<MoviePlay> getMoviePlaysByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id =" + movieId + " ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        List<MoviePlay> moviePlayList = generateMoviePlays(rs);

        return  moviePlayList;
    }

    public List<MoviePlay> findMoviePlaysByTheater(int theaterId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE theater_id =" + theaterId + " ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateMoviePlays(rs);
    }

    public MoviePlay getMoviePlayById(int id) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE play_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, id);

        MoviePlay moviePlay = generateMoviePlay(rs);

        return moviePlay;

    }

    public int addMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "INSERT INTO movie_plays(movie_id, theater_id, play_start) VALUES(?, ?, ?)";

        return jdbcTemplate.update(sqlQuery, moviePlay.getMovieId(), moviePlay.getTheaterId(), moviePlay.getPlayStart());
    }

    public void deleteMoviePlay(int id) {
        String sqlQuery = "DELETE FROM movie_plays WHERE play_id = ?";

        jdbcTemplate.update(sqlQuery, id);
    }

    public void editMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "UPDATE movie_plays SET movie_id = ?, theater_id = ?, play_start = ? WHERE play_id = ?";

        jdbcTemplate.update(sqlQuery, moviePlay.getMovieId(), moviePlay.getTheaterId(), moviePlay.getPlayStart(), moviePlay.getId());
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
