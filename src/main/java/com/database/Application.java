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
        //URL url = getClass().getResource("/com/database/view.fxml");
	    //URL url = getClass().getResource("Field.fxml");
        
	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Field.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Title Goes Here");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
