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

        // Function to deserialize an object of type Session
        public static String DeserializeS(String filename) {
            Session sessionDeserialize = null;
            try {
                FileReader obj1 = new FileReader(filename);
                Gson gson = new Gson();
                sessionDeserialize = gson.fromJson(obj1, Session.class);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return sessionDeserialize.toString();
            }
        }

        // Function to deserialize an object of type Movie
        public static String DeserializeM(String filename) {
            Movie movieDeserialize = null;
            try {
                FileReader obj1 = new FileReader(filename);
                Gson gson = new Gson();
                movieDeserialize = gson.fromJson(obj1, Movie.class);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return movieDeserialize.getMovie();
            }
        }


        // Function to deserialize an object of type Theater
        public static String DeserializeT(String filename) {
            Theater theaterDeserialize = null;
            try {
                FileReader obj1 = new FileReader(filename);
                Gson gson = new Gson();
                theaterDeserialize = gson.fromJson(obj1, Theater.class);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return theaterDeserialize.getName();
            }
        }


        // Function to serialize object using ObjectOutputStream

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

        public static void main(String[] args) throws FileNotFoundException {

            // Deserialize objects from JSON files
            String movie1deserialize = DeserializeM("movie1JSON.txt");
            System.out.println(movie1deserialize);
            String movie2deserialize = DeserializeM("movie2JSON.txt");
            String session1deserialize = DeserializeS("session1JSON.txt");
            System.out.println(session1deserialize);
            String session2deserialize= DeserializeS("session2JSON.txt");
            String theater1deserialize = DeserializeT("theater1JSON.txt");
            String theater2deserialize = DeserializeT("theater2JSON.txt");

            // Serialize objects using ObjectOutputStream
            ObjectOutputStream("session1OBJ.txt", session1deserialize);
            ObjectOutputStream("session2OBJ.txt", session2deserialize);
            ObjectOutputStream("movie1OBJ.txt", movie1deserialize);
            ObjectOutputStream("movie2OBJ.txt", movie2deserialize);
            ObjectOutputStream("theater1OBJ.txt", theater1deserialize);
            ObjectOutputStream("theater2OBJ.txt", theater2deserialize);



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