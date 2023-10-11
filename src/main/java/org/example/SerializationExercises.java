package org.example;


import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;


public class SerializationExercises {
    /*
        Should define the class for the concepts Movie, Theater and Session.
        A session is a play of movie in a theater.
        Create 2 instances of each class and relate among them.
        Serialize to Json all objects and save then in different files.
     */

    public static class Exercise1 {

        // Function that write Json object in a file
        public static void writeJsonToFile(String filePath, String jsonString) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Write element on the file
                writer.write(jsonString);
                System.out.println("The element was save correctly.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void main(String[] args) {
            // Create two instances of each class
            Movie movie1 = new Movie("Mamma Mia");
            Movie movie2 = new Movie("101 Dalmatas");

            Theater theater1 = new Theater("Yelmo Cines");
            Theater theater2 = new Theater("Cinesa");

            Session session1 = new Session(movie1, theater1);
            Session session2 = new Session(movie2, theater2);

            // Serializing objects to JSON
            Gson gson = new Gson();
            String movie1Json = gson.toJson(movie1);
            String movie2Json = gson.toJson(movie2);
            String theater1Json = gson.toJson(theater1);
            String theater2Json = gson.toJson(theater2);
            String session1Json = gson.toJson(session1);
            String session2Json = gson.toJson(session2);

            // Saving the objects in different files
            writeJsonToFile("movie1JSON.txt", movie1Json);
            writeJsonToFile("movie2JSON.txt", movie2Json);
            writeJsonToFile("theater1JSON.txt", theater1Json);
            writeJsonToFile("theater2JSON.txt", theater2Json);
            writeJsonToFile("session1JSON.txt", session1Json);
            writeJsonToFile("session2JSON.txt", session2Json);

        }

    }


    /*
        Deserialize the objects created in exercise 1.
        Now serialize them using ObjectOutputStream
     */
    public static class Exercise2 {

        // Function that Deserialize objects from a file
        public static void Deserialize(String filename, String type) {
            Gson gson = new Gson();
            try {
                FileReader obj1 = new FileReader(filename);
                if (type == "s") {
                    Session session = gson.fromJson(obj1, Session.class);
                    System.out.println("Converting from JSON to an object session:\n" + session.toString());
                } else if (type == "m") {
                    Movie movie = gson.fromJson(obj1, Movie.class);
                    System.out.println("Converting from JSON to an object movie:\n" + movie.getMovie());
                } else if (type == "t") {
                    Theater theater = gson.fromJson(obj1, Theater.class);
                    System.out.println("Converting from JSON to an object movie:\n" + theater.getName());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }


        public static void main(String[] args) throws FileNotFoundException {

            // Deserialize objects from JSON files
            Deserialize("movie1JSON.txt","m");
            Deserialize("movie2JSON.txt","m");
            Deserialize("session1JSON.txt","s");
            Deserialize("session2JSON.txt","s");
            Deserialize("theater1JSON.txt","t");
            Deserialize("theater2JSON.txt","t");

/*

            File file = new File("C:\\Users\\lucia\\IdeaProjects\\clase3oct\\session1JSON.txt");
            BufferedReader br;
            Session objDeserialized = null;
            br = new BufferedReader(new FileReader(file));
            try {
                while (true) {
                    String text = br.readLine();
                    if (text == null) break;
                    Gson gson = new Gson();
                    objDeserialized = gson.fromJson(text, Session.class);
                    System.out.println("Converting from JSON session1:\n" + objDeserialized.toString());
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


 */
           /*
            FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(objDeserialized);
                objectOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            */

        }
    }

    /*
       Deserialize the objects from the binary files created in exercise 2.
    */
    public static class Exercise3 {

        public static void main(String[] args) throws FileNotFoundException {

            FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Session s2 = (Session)objectInputStream.readObject();
                System.out.println("Read with ObjectOutputStream: " + s2);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}