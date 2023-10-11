package org.example;

import java.io.*;
import java.util.Arrays;

class Session implements Serializable{
    Movie movie;
    Theater theater;


    public Session(Movie movie, Theater theater) {
        this.movie = movie;
        this.theater = theater;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    @Override
    public String toString(){
        return Arrays.asList(movie.getMovie(), theater.getName()).toString();

    }

}