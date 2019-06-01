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

    public List<MoviePlay> findNextMoviePlays() {
        int limit = 6;

        String sqlQuery = "SELECT * FROM movie_plays ORDER BY play_start LIMIT ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, limit);

        return generateMoviePlays(rs);
    }

    public List<MoviePlay> findMoviePlaysByMovieId(int movieId) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE movie_id = ? ORDER BY play_start";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, movieId);

        List<MoviePlay> moviePlayList = generateMoviePlays(rs);

        return  moviePlayList;
    }

    public MoviePlay findMoviePlayById(int id) {
        String sqlQuery = "SELECT * FROM movie_plays WHERE play_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlQuery, id);

        MoviePlay moviePlay = generateMoviePlay(rs);

        return moviePlay;

    }

    public void addMoviePlay(MoviePlay moviePlay) {
        String sqlQuery = "INSERT INTO movie_plays(movie_id, theater_id, play_start) VALUES(?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, moviePlay.getMovieId());
                ps.setInt(2, moviePlay.getTheaterId());
                ps.setTimestamp(3, Timestamp.valueOf(moviePlay.getPlayStart()));

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
        String sqlQuery = "UPDATE movie_plays SET movie_id = ?, theater_id = ?, play_start = ? WHERE play_id = ?";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlQuery);
                ps.setInt(1, moviePlay.getMovieId());
                ps.setInt(2, moviePlay.getTheaterId());
                ps.setTimestamp(3, Timestamp.valueOf(moviePlay.getPlayStart()));
                ps.setInt(4, moviePlay.getId());
                return ps;
            }
        };

        jdbcTemplate.update(sqlQuery, moviePlay.getMovieId(), moviePlay.getTheaterId(), moviePlay.getPlayStart(), moviePlay.getId());
    }

    private MoviePlay generateMoviePlay(SqlRowSet rs) {
        MoviePlay moviePlay = new MoviePlay();

        while(rs.next()) {
            moviePlay.setId(rs.getInt("play_id"));
            moviePlay.setMovieId(rs.getInt("movie_id"));
            moviePlay.setTheaterId(rs.getInt("theater_id"));

            Timestamp tsStart = rs.getTimestamp("play_start");
            moviePlay.setPlayStart(tsStart.toLocalDateTime());
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
            moviePlay.setPlayStart(tsStart.toLocalDateTime());

            moviePlayList.add(moviePlay);
        }

        return moviePlayList;
    }
}
