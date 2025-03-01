package com.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.util.OpencsvUtils;
import com.opencsv.exceptions.CsvException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.lang.ProcessBuilder;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

import org.h2.tools.RunScript;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("/com/database/view.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setTitle("Title Goes Here");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
       try {
           String dbPath = "jdbc:h2:~/Desktop/myDB/myDB";
           String scriptPath = "src/main/resources/com/database/db.sql";

           Methods.dropTable("myTable", dbPath);
           Connection connection = DriverManager.getConnection(dbPath);
           FileReader reader = new FileReader(scriptPath);
           RunScript.execute(connection, reader);
           Statement s = connection.createStatement();
           ResultSet resultset = s.executeQuery("Show columns from mytable");
           while (resultset.next()) {
               System.out.println(resultset.getString(1));
           }
           System.out.print("connectin  is valid : " + connection.isValid(20));
       } catch (Exception e) {e.printStackTrace();}
    }
}