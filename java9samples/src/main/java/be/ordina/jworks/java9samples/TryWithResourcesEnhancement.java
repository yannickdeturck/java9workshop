package be.ordina.jworks.java9samples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Try-with-resources enhancement.
 *
 * @author Yannick De Turck
 */
public class TryWithResourcesEnhancement {
    public static void main(String[] args) throws IOException {
        // Java 7
        BufferedReader reader1 = new BufferedReader(new FileReader("file.txt"));
        try (BufferedReader reader2 = reader1) {
            System.out.println(reader2.readLine());
        }

        // Java 9
        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        try (reader) {
            System.out.println(reader.readLine());
        }
    }
}
