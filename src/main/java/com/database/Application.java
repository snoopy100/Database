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
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 428);
        stage.setTitle("Title Goes Here");
        stage.setScene(scene);
        stage.show();

        System.out.println(stage.getWidth() + "\n" + stage.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}
