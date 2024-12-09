package org.example;

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Database.connectDB();
            Database.createTableMovie();

            var genMovies= MovieHandler.generateMovies(30);
            for (Movie movie : genMovies){
                Database.addMovie(movie);
            }


            List<Movie> movies = Database.getAllMovies();

            Map<String, Integer> genreCounts = new HashMap<>();
            for (Movie movie : movies) {
                genreCounts.put(movie.getGenre(), genreCounts.getOrDefault(movie.getGenre(), 0) + 1);
            }

            SwingUtilities.invokeLater(() -> {
                Graph graph = new Graph(genreCounts);
                graph.setSize(800, 600);
                graph.setLocationRelativeTo(null);
                graph.setVisible(true);
            });

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            Database.closeDB();
        }
    }
}

