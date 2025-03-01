package com.database;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.lang.ProcessBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import org.h2.tools.RunScript;

public class Application extends javafx.application.Application {
    public void runCommands(String[] commands) {
        Scanner scanner = null;
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = null;
            for (int i = 0; i < commands.length; i++) {
                pr = rt.exec(commands[i]);
            }
            scanner = new Scanner(pr.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Title Goes Here");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();

        try {
            // opens up to db on ~/Desktop
            //Connection connection = DriverManager.getConnection("jdbc:h2:~/Desktop/myDB");
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
            FileReader reader = new FileReader("/Users/jacksonkotch/Desktop/Database/src/main/resources/com/database/db.sql");
            RunScript.execute(connection, reader);
            System.out.print("connectin  is valid : " + connection.isValid(0));
        } catch (Exception e) {e.printStackTrace();}
    }
}