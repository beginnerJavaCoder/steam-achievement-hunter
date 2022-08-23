package com.example.app;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class App {

    protected static String API_KEY;
    protected static String ACCOUNT_ID;

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You need to specify the path to your Steam API Key");
            return;
        }

        try {
            initCredentials(args[0]);
        } catch (InvalidPathException e) {
            System.out.println("The chosen path is improper. " + e.getMessage());
            return;
        } catch (NoSuchFileException e) {
            System.out.println("There's no such file. Check the accuracy of the chosen path: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("The file can't be read by the app");
            return;
        }

        if (API_KEY == null) {
            System.out.println("API KEY is not defined. Try to check the chosen file or the accuracy of the key itself");
            return;
        }
        if (ACCOUNT_ID == null) {
            System.out.println("ACCOUNT ID is not defined. Try to check the chosen file or the accuracy of the id itself");
            return;
        }


    }

    protected static void initCredentials(String path) throws IOException {
        Path filePath = Paths.get(path);
        List<String> properties = Files.readAllLines(filePath);
        for (String property : properties) {
            if (property.startsWith("API KEY")) {
                API_KEY = property.substring(8);
            }
            if (property.startsWith("ACCOUNT ID")) {
                ACCOUNT_ID = property.substring(11);
            }
        }
    }
}
