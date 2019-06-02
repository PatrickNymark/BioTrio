package com.final_project.repositories;

import com.final_project.entities.MoviePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MoviePlayRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MoviePlay> findAllMoviePlays() {
        String sqlQuery = "SELECT * FROM movie_plays ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery);

        return generateMoviePlays(rs);
    }

    public List<MoviePlay> findNextMoviePlays() {
        int limit = 6;

        String sqlQuery = "SELECT * FROM movie_plays ORDER BY play_start LIMIT ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, limit);

        return generateMoviePlays(rs);
    }

    public List<MoviePlay> findMoviePlaysByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id = ? ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, movieId);

        return  generateMoviePlays(rs);
    }

    public List<MoviePlay> findPlaysByMovieIdWithinDate(int movieId, LocalDate startLimit, LocalDate endLimit) {
        System.out.println(startLimit);
        System.out.println(endLimit);
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id = ? AND play_start >= ? AND play_start <= ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, movieId, startLimit, endLimit);

        return  generateMoviePlays(rs);
    }


    public MoviePlay findMoviePlayById(int id) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE play_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, id);

        return generateMoviePlay(rs);
    }

    public List<MoviePlay> findMoviePlaysByTheaterId(int theaterId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE theater_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, theaterId);

        return generateMoviePlays(rs);
    }

    public void addMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "INSERT INTO movie_plays(movie_id, theater_id, play_start, play_end) VALUES(?, ?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, moviePlay.getMovieId());
                ps.setInt(2, moviePlay.getTheaterId());
                ps.setTimestamp(3, Timestamp.valueOf(moviePlay.getPlayStart()));
                ps.setTimestamp(4, Timestamp.valueOf(moviePlay.getPlayEnd()));

                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    public void deleteMoviePlay(int id) {
        String sqlQuery = "DELETE FROM movie_plays WHERE play_id = ?";

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

    public void editMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "UPDATE movie_plays SET movie_id = ?, theater_id = ?, play_start = ?, play_end = ? WHERE play_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, moviePlay.getMovieId());
                ps.setInt(2, moviePlay.getTheaterId());
                ps.setTimestamp(3, Timestamp.valueOf(moviePlay.getPlayStart()));
                ps.setTimestamp(4, Timestamp.valueOf(moviePlay.getPlayEnd()));
                ps.setInt(5, moviePlay.getId());
                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }

    private MoviePlay generateMoviePlay(SqlRowSet rs) {
        MoviePlay moviePlay = new MoviePlay();

        while(rs.next()) {
            moviePlay.setId(rs.getInt("play_id"));
            moviePlay.setMovieId(rs.getInt("movie_id"));
            moviePlay.setTheaterId(rs.getInt("theater_id"));

            Timestamp tsStart = rs.getTimestamp("play_start");
            Timestamp tsEnd = rs.getTimestamp("play_end");
            moviePlay.setPlayStart(tsStart.toLocalDateTime());
            moviePlay.setPlayEnd(tsEnd.toLocalDateTime());
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

            Timestamp tsStart = rs.getTimestamp("play_start");
            Timestamp tsEnd = rs.getTimestamp("play_end");
            moviePlay.setPlayStart(tsStart.toLocalDateTime());
            moviePlay.setPlayEnd(tsEnd.toLocalDateTime());

            System.out.println(moviePlay);
            moviePlayList.add(moviePlay);
        }

        return moviePlayList;
    }
}
