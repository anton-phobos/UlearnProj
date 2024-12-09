package org.example;

public class AdultMovie extends Movie{
    private int minAge;

    public AdultMovie(String title, String director, int duration, String genre) {
        super(title, director, duration, genre);
        this.minAge = minAge;
    }

    public int getMinAge() {
        return this.minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Минимальный возраст: " + this.minAge + " лет");
    }

    public String toString() {
        String var10000 = super.toString();
        return "(AdultMovie)/ " + var10000 + "/ Минимальный возраст: " + this.minAge + " лет";
    }
}
