package com.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML TableView<PatientFile> tableView;
    @FXML MenuButton menu;
    MenuItem dateItem = new MenuItem("Date");
    MenuItem nameItem = new MenuItem("Name");
    MenuItem mrnItem = new MenuItem("MRN");
    MenuItem etmItem = new MenuItem("ETM");
    // add something to filter by procedures
    ArrayList<MenuItem> itemsList = new ArrayList<MenuItem>();
    String path;

    public void instantiteList() {
        itemsList.add(dateItem);
        itemsList.add(nameItem);
        itemsList.add(mrnItem);
        itemsList.add(etmItem);

        for (MenuItem item : itemsList) {
            item.setOnAction(e -> {
                menu.setText(item.getText());
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File dbFile = new File("dbName.txt");
            BufferedReader br = new BufferedReader(new FileReader(dbFile));
            String path = br.readLine();
            update();
        } catch(Exception e) {e.printStackTrace();}
        instantiteList();
        menu.getItems().addAll(itemsList);
    }

    @FXML
    public void update() {
        try {
            File dbFile = new File("dbName.txt");
            BufferedReader br = new BufferedReader(new FileReader(dbFile));
            String path = br.readLine();

            //------------------------
            ObservableList<PatientFile> data = FXCollections.observableArrayList();
            data.add(new PatientFile("1", "2", "3", "4", "5"));
            Connection connection = DriverManager.getConnection("jdbc:h2:" + path);
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("select * from myTable");
            while (resultSet.next()) {
                data.add(new PatientFile(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
            data.sort(new Comparator<PatientFile>() {
                @Override
                public int compare(PatientFile o1, PatientFile o2) {
                    return o1.compare(o2);
                }
            });
            tableView.setItems(data);
        } catch (Exception e) {e.printStackTrace();}
    }
}
