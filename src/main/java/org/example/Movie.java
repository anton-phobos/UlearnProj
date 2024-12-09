package org.example;

import lombok.Getter;
import lombok.Setter;

public class Movie{
    @Setter
    @Getter
    private String title;
    @Getter
    private String director;
    @Getter
    private int duration;
    @Getter
    private boolean isAvailable;
    @Getter
    public String genre;

    public Movie(String title, String director, int duration, String genre) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.isAvailable = true;
    }

    public void setDuration(int duration) {
        if (duration > 0) {
            this.duration = duration;
        } else {
            System.out.println("Длительность фильма должна быть положительной.");
        }
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void changeAvailability(boolean status) {
        this.isAvailable = status;
    }

    public void printInfo() {
        System.out.println("Название: " + this.title);
        System.out.println("Режиссер: " + this.director);
        System.out.println("Длительность: " + this.duration + " минут");
        System.out.println("Жанр: " + this.genre);
        System.out.println("Доступен: " + (this.isAvailable ? "Да" : "Нет"));
    }

    public int getMinAge() {
        return 18;
    }

    public String toString() {
        return "Название: " + this.title + "/ Режиссер: " + this.director + "/ Длительность: " + this.duration + " минут/ Жанр: " + this.genre + "/ Доступен: " + (this.isAvailable ? "Да" : "Нет");
    }
}
