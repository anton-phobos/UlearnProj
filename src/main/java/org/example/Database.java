package org.example;

import org.example.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    public static Connection connection;
    public static Statement statement;

    public Database() {
    }

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void createTableMovie() throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS movie (" +
                "title TEXT, " +
                "director TEXT, " +
                "duration INTEGER, " +
                "genre TEXT, " +
                "isAvailable BOOLEAN)");
    }

    public static void addMovie(Movie movie) throws SQLException {
        String query = String.format("INSERT INTO movie (title, director, duration, genre, isAvailable) " +
                        "VALUES ('%s', '%s', %d, '%s', %b)",
                movie.getTitle(), movie.getDirector(), movie.getDuration(), movie.getGenre(), movie.isAvailable());
        statement.execute(query);
    }

    public static ArrayList<Movie> getAllMovies() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        String query = "SELECT title, director, duration, genre, isAvailable FROM movie";
        ResultSet resultSet = statement.executeQuery(query);

        try {
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                int duration = resultSet.getInt("duration");
                String genre = resultSet.getString("genre");
                boolean isAvailable = resultSet.getBoolean("isAvailable");

                Movie movie = new Movie(title, director, duration, genre);
                movie.changeAvailability(isAvailable);
                movies.add(movie);
            }
        } catch (Throwable e) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Throwable suppressed) {
                    e.addSuppressed(suppressed);
                }
            }
            throw e;
        }

        if (resultSet != null) {
            resultSet.close();
        }

        return movies;
    }

    public static void closeDB() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database: " + e.getMessage());
        }
    }
}
