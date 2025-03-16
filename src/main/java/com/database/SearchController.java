package com.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML TableView<PatientFile> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        File dbFile = new File("dbName.txt");
        BufferedReader br = new BufferedReader(new FileReader(dbFile));
        String path = br.readLine();

        Connection connection = DriverManager.getConnection("jdbc:h2:" + path);
        Statement s = connection.createStatement();
        ResultSet resultSet = s.executeQuery("select * from myTable");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        System.out.print("connectin  is valid : " + connection.isValid(20));
    } catch (Exception e) {e.printStackTrace();}
        update();
    }

    @FXML
    public void update() {
        ObservableList<PatientFile> data = FXCollections.observableArrayList();
        data.add(new PatientFile("1", "2", "3", "4", "5"));

        tableView.setItems(data);
    }
}
