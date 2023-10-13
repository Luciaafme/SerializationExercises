package org.example;


import java.io.*;

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
            writeJsonToFile("movie1.txt", movie1Json);
            writeJsonToFile("movie2.txt", movie2Json);
            writeJsonToFile("theater1.txt", theater1Json);
            writeJsonToFile("theater2.txt", theater2Json);
            writeJsonToFile("session1.txt", session1Json);
            writeJsonToFile("session2.txt", session2Json);

        }

    }


    /*
        Deserialize the objects created in exercise 1.
        Now serialize them using ObjectOutputStream
     */
    public static class Exercise2 {

        // Function that deserialize objects from the files created in exercise 1
        // and then serialize them using ObjectOutputStream
        public static void DeserializeJSON_SerializeOOS(String fileName, String type) {
            Gson gson = new Gson();
            try {
                FileReader obj1 = new FileReader(fileName);
                if (type.equals("s")) {
                    Session SessionDeserialized = gson.fromJson(obj1, Session.class);
                    System.out.println("Deserializing from JSON to an object session:\n" + SessionDeserialized.toString());
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                        objectOutputStream.writeObject(SessionDeserialized);
                        objectOutputStream.flush();
                        System.out.println("Serializing from object to file (OOS) :\n" + fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("m")) {
                    Movie MovieDeserialized = gson.fromJson(obj1, Movie.class);
                    System.out.println("Deserializing from JSON to an object movie:\n" + MovieDeserialized.getMovie());
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                        objectOutputStream.writeObject(MovieDeserialized);
                        objectOutputStream.flush();
                        System.out.println("Serializing from object to file (OOS) :\n" + fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("t")) {
                    Theater TheaterDeserialized = gson.fromJson(obj1, Theater.class);
                    System.out.println("Deserializing from JSON to an object theater:\n" + TheaterDeserialized.getName());
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                        objectOutputStream.writeObject(TheaterDeserialized);
                        objectOutputStream.flush();
                        System.out.println("Serializing from object to file (OOS) :\n" + fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws FileNotFoundException {

            // Testing the function DeserializeJSON_SerializeOOS
            DeserializeJSON_SerializeOOS("movie1.txt", "m");
            DeserializeJSON_SerializeOOS("movie2.txt", "m");
            DeserializeJSON_SerializeOOS("theater1.txt", "t");
            DeserializeJSON_SerializeOOS("theater2.txt", "t");
            DeserializeJSON_SerializeOOS("session1.txt", "s");
            DeserializeJSON_SerializeOOS("session2.txt", "s");


/*
            File file = new File("session1JSON.txt");
            BufferedReader br;
            Session objDeserialized = null;
            Gson gson = new Gson();
            br = new BufferedReader(new FileReader(file));
            try {
                while (true) {
                    String text = br.readLine();
                    if (text == null) break;
                    objDeserialized = gson.fromJson(text, Session.class);
                    System.out.println("Converting from JSON session1:\n" + objDeserialized.toString());
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            FileOutputStream fileOutputStream = new FileOutputStream("yourfileSession1.txt");

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

            // Function that deserialize the object from the files created with ObjectOutputStream
            public static void DeserializeOOS(String fileName, String type) {
                Gson gson = new Gson();
                if (type.equals("s")) {
                    try (FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                        Session SessionDeserialized = (Session) objectInputStream.readObject();
                        System.out.println("Deserializing from OOS to an object session:\n" + SessionDeserialized.toString());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("m")) {
                    try (FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                        Movie MovieDeserialized = (Movie) objectInputStream.readObject();
                        System.out.println("Deserializing from OOS to an object movie:\n" + MovieDeserialized.getMovie());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type.equals("t")){
                    try (FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                        Theater TheaterDeserialized = (Theater) objectInputStream.readObject();
                        System.out.println("Deserializing from OOS to an object theater:\n" + TheaterDeserialized.getName());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            public static void main(String[] args) throws FileNotFoundException {

                // Testing the function DeserializeOOS
                DeserializeOOS("movie1.txt", "m");
                DeserializeOOS("movie2.txt", "m");
                DeserializeOOS("theater1.txt", "t");
                DeserializeOOS("theater2.txt", "t");
                DeserializeOOS("session1.txt", "s");
                DeserializeOOS("session2.txt", "s");
/*
            FileInputStream fileInputStream = new FileInputStream("yourfileSession1.txt");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Session s2 = (Session)objectInputStream.readObject();
                System.out.println("Deserialize using ObjectInputStream: " + s2);
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

 */
            }
        }
}