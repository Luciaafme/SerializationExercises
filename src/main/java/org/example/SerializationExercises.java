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
        public static Session DeserializeS(String fileName) {
            File file = new File(fileName);
            Gson gson = new Gson();
            Session objDeserialized = null;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String text;
                while ((text = br.readLine()) != null) {
                    objDeserialized = gson.fromJson(text, Session.class);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return objDeserialized;
        }

        public static void ObjectOutputStreamS(Session session, String fileName) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                objectOutputStream.writeObject(session);
                objectOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void main(String[] args) throws FileNotFoundException {


            // Leer y deserializar desde archivos de texto
            Session session1Deserialize = DeserializeS("session1JSON.txt");
            Session session2Deserialize = DeserializeS("session2JSON.txt");

            // Imprimir los objetos deserializados
            System.out.println("Converting from JSON to an object session1:\n" + session1Deserialize.toString());
            System.out.println("Converting from JSON to an object session2:\n" + session2Deserialize.toString());

            // Serializar y escribir en archivos
            ObjectOutputStreamS(session1Deserialize, "Session1OBJ.txt");
            ObjectOutputStreamS(session2Deserialize, "Session2OBJ.txt");


/*
            File file = new File("session1JSON.txt");
            File file2 = new File("session2JSON.txt");
            BufferedReader br, br2;
            Session objDeserialized = null;
            Session objDeserialized2 = null;
            Gson gson = new Gson();
            br = new BufferedReader(new FileReader(file));
            br2 = new BufferedReader(new FileReader(file2));
            try {
                while (true) {
                    String text = br.readLine();
                    String text2 = br2.readLine();
                    if (text == null) break;
                    objDeserialized = gson.fromJson(text, Session.class);
                    System.out.println("Converting from JSON session1:\n" + objDeserialized.toString());
                    objDeserialized2 = gson.fromJson(text2, Session.class);
                    System.out.println("Converting from JSON session2:\n" + objDeserialized2.toString());
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

            public static Session readSessionFromFile(String fileName) {
                try (FileInputStream fileInputStream = new FileInputStream(fileName);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                    Session session = (Session) objectInputStream.readObject();
                    return session;
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            public static void main(String[] args) throws FileNotFoundException {

                // Leer y deserializar desde el archivo
                Session session = readSessionFromFile("Session1OBJ.txt");

                // Imprimir el objeto deserializado
                System.out.println("Deserialize object of type Session: " + session);
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