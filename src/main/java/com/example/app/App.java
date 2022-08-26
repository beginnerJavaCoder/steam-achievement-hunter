package com.example.app;

import com.example.app.api.CredentialsManager;
import com.example.app.api.CredentialsManagerInitializationException;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

public class App {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You need to specify the path to your Steam API Key");
            return;
        }

        try {
            CredentialsManager.getInstance().init(args[0]);
        } catch (InvalidPathException e) {
            System.out.println("The chosen path is improper. " + e.getMessage());
            return;
        } catch (NoSuchFileException e) {
            System.out.println("There's no such file. Check the accuracy of the chosen path: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("The file can't be read by the app");
            return;
        } catch (CredentialsManagerInitializationException e) {
            System.out.println("Exception during initialization of credentials: " + e.getMessage());
            return;
        }
    }
}
