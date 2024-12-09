package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieHandler {

    public MovieHandler() {
    }
    public static void writeFile(List<Movie> movies, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Movie movie : movies) {
                writer.write(movie.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static List<Movie> readFile(String path) {
        List<Movie> movies = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                movies.add(parseMovie(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return movies;
    }

    private static Movie parseMovie(String line) {
        if (line.startsWith("(AdultMovie)")) {
            return parseAdultMovie(line);
        } else {
            return parseRegularMovie(line);
        }
    }

    private static AdultMovie parseAdultMovie(String line) {
        String[] parts = line.split("/ ");
        String title = parts[1].split(": ")[1];
        String director = parts[2].split(": ")[1];
        int duration = Integer.parseInt(parts[3].split(": ")[1].split(" ")[0]);
        String genre = parts[4].split(": ")[1];
        boolean isAvailable = parts[5].split(": ")[1].equals("Да");
        int minAge = Integer.parseInt(parts[6].split(": ")[1].split(" ")[0]);

        AdultMovie movie = new AdultMovie(title, director, duration, genre);
        movie.changeAvailability(isAvailable);
        movie.setMinAge(minAge);
        return movie;
    }

    private static Movie parseRegularMovie(String line) {
        String[] parts = line.split("/ ");
        String title = parts[0].split(": ")[1];
        String director = parts[1].split(": ")[1];
        int duration = Integer.parseInt(parts[2].split(": ")[1].split(" ")[0]);
        String genre = parts[3].split(": ")[1];
        boolean isAvailable = parts[4].split(": ")[1].equals("Да");

        Movie movie = new Movie(title, director, duration, genre);
        movie.changeAvailability(isAvailable);
        return movie;
    }

    public static List<Movie> generateMovies(int count) {
        List<Movie> movies = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextBoolean()) {
                movies.add(generateRandomMovie());
            } else {
                movies.add(generateRandomAdultMovie());
            }
        }
        return movies;
    }

    private static Movie generateRandomMovie() {
        String[] titles = {"Inception", "The Matrix", "Titanic", "The Dark Knight", "Avatar"};
        String[] directors = {"Christopher Nolan", "James Cameron", "Steven Spielberg", "Quentin Tarantino", "Martin Scorsese"};
        String[] genres = {"Science Fiction", "Action", "Romance", "Drama", "Thriller"};

        String title = randomFromArray(titles);
        String director = randomFromArray(directors);
        String genre = randomFromArray(genres);
        int duration = 90 + new Random().nextInt(91);
        return new Movie(title, director, duration, genre);
    }

    private static AdultMovie generateRandomAdultMovie() {
        String[] titles = {"Fifty Shades of Grey", "Basic Instinct", "Blue Is the Warmest Color", "Eyes Wide Shut"};
        String[] directors = {"Sam Taylor-Johnson", "Paul Verhoeven", "Abdellatif Kechiche", "Stanley Kubrick"};
        String[] genres = {"Romance", "Drama", "Erotic"};

        String title = randomFromArray(titles);
        String director = randomFromArray(directors);
        String genre = randomFromArray(genres);
        int duration = 90 + new Random().nextInt(91);
        int minAge = 18 + new Random().nextInt(3);
        AdultMovie movie = new AdultMovie(title, director, duration, genre);
        movie.setMinAge(minAge);
        return movie;
    }

    private static String randomFromArray(String[] array) {
        return array[new Random().nextInt(array.length)];
    }
}
