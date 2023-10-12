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

        static Session session1deserialize;
        static Session session2deserialize;
        static Movie movie1deserialize;
        static Movie movie2deserialize;
        static Theater theater1deserialize;
        static Theater theater2deserialize;



        public Exercise2() {
        }

        // Function that Deserialize objects from a file
        public static void Deserialize(String filename, String type) {
            Gson gson = new Gson();
            try {
                FileReader obj1 = new FileReader(filename);
                if (type == "s1") {
                    session1deserialize = gson.fromJson(obj1, Session.class);
                    System.out.println("Converting from JSON to an object session1:\n" + session1deserialize.toString());
                }else if (type == "s2"){
                    session2deserialize = gson.fromJson(obj1, Session.class);
                    System.out.println("Converting from JSON to an object session2:\n" + session2deserialize.toString());
                } else if (type == "m1") {
                     movie1deserialize = gson.fromJson(obj1, Movie.class);
                    System.out.println("Converting from JSON to an object movie1:\n" + movie1deserialize.getMovie());
                } else if (type == "m2") {
                     movie2deserialize = gson.fromJson(obj1, Movie.class);
                    System.out.println("Converting from JSON to an object movie2:\n" + movie2deserialize.getMovie());
                } else if (type == "t1") {
                     theater1deserialize = gson.fromJson(obj1, Theater.class);
                    System.out.println("Converting from JSON to an object theater1:\n" + theater1deserialize.getName());
                } else if (type == "t2") {
                     theater2deserialize = gson.fromJson(obj1, Theater.class);
                    System.out.println("Converting from JSON to an object theater2:\n" + theater2deserialize.getName());
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Function to serialize object using ObjectOutputStream
/*
        public static void ObjectOutputStream(String filename, String objDeserialized) throws FileNotFoundException {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
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
        }



 */


        public static void main(String[] args) throws FileNotFoundException {

            // Deserialize objects from JSON files
            Deserialize("movie1JSON.txt","m1");
            Deserialize("movie2JSON.txt","m2");
            Deserialize("session1JSON.txt","s1");
            Deserialize("session2JSON.txt","s2");
            Deserialize("theater1JSON.txt","t1");
            Deserialize("theater2JSON.txt","t2");




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