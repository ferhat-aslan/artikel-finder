package com.artikelfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.util.ResourceUtils;

public class FileSearch {

    /*
     * public void Test(){
     * String filePath = "path/to/your/file.txt"; // Replace with your file path
     * String searchTerm = "searchTerm"; // Replace with the term you want to search
     * for
     * 
     * try (InputStream inputStream = new FileInputStream(filePath);
     * BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
     * StandardCharsets.UTF_8))) {
     * 
     * String line;
     * while ((line = reader.readLine()) != null) {
     * if (line.contains(searchTerm)) {
     * System.out.println("Found: " + line);
     * }
     * }
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
    /*
     * static void getResourceFileAsString() throws IOException {
     * ClassLoader classLoader = ClassLoader.getSystemClassLoader();
     * try (InputStream is = classLoader.getResourceAsStream("/de-en.txt")) {
     * 
     * try (InputStreamReader isr = new InputStreamReader(is);
     * BufferedReader reader = new BufferedReader(isr)) {
     * reader.lines().forEach(e -> System.out.println(e));
     * }
     * }
     * }
     */

    public void test() {
        String filePath = "src/main/resources/de-en.txt"; // Replace with your file path
        String searchTerm = "Tür"; // Replace with the term you want to search for

        try (InputStream inputStream = new FileInputStream(filePath);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    System.out.println("Found: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test2() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/de-en.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = reader.lines().toList();
            System.out.println(lines.get(0));
        }
    }

    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
                                                     // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext)
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }

    public List<String> getResultList(List<String> lines, String searchStr) {
        // found exact line
        String exact = "NotFound";
        List<String> foundList = new ArrayList<String>();
        for (String line : lines) {
            int lent = searchStr.length();

            if (line.substring(0, lent).toLowerCase().equals(searchStr.toLowerCase())

                    && line.substring(searchStr.length(), (searchStr.length() + 2)).toLowerCase().equals(" {")

            ) {
                exact = line.substring(0, line.indexOf("}")); // get Artikel
            } else {
                /*
                 * System.out.println(line.indexOf("}"));
                 * foundList.add(line.substring(0, 5));
                 */
            }
        }
        foundList.add(exact);

        return foundList;

    }

    public List<String> parseFile(String fileName, String searchStr) throws IOException {
        // Path path = Paths.get(fileName);
        List<String> list = new ArrayList<String>();

        /*
         * try {
         * // Read all lines from the file as a List of Strings
         * List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
         * 
         * // Print each line
         * for (String line : lines) {
         * if (line.toLowerCase().contains(searchStr)) {
         * list.add(line);
         * // System.out.println("Found: " + line);
         * }
         * }
         * 
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
        try (InputStream inputStream = getClass().getResourceAsStream("/de-en.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF8"))) {
            List<String> lines = reader.lines().toList();
            for (String line : lines) {
                if (line.toLowerCase().contains(searchStr)) {
                    list.add(line);
                    // System.out.println("Found: " + line);
                }
            }
        }
        // getResourceFileAsString();
        // test2();
        return getResultList(list, searchStr);

    }
}