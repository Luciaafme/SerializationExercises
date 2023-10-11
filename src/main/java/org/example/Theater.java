package org.example;
import java.io.*;
class Theater  implements Serializable{
    private String theater;
    public Theater(String name) {
        this.theater = name;
    }

    public String getName() {
        return theater;
    }

    public void setName(String name) {
        this.theater = name;
    }
}
