package org.example;
import java.io.*;
public class Movie  implements Serializable {
    private String movie;
    public Movie(String title) {

        this.movie = title;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
