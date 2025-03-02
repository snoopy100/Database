package com.database;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.h2.tools.RunScript;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    @FXML HBox bar;
    @FXML Button openButton;
    @FXML Button selectButton;
    @FXML Button createButton;
    @FXML AnchorPane pane;

    DirectoryChooser chooser;
    String fileDir = "~/";
    String dbName;

    @FXML
    public void select(Event e) {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(pane.getScene().getWindow());

        fileDir = file.getAbsolutePath();
        System.out.println(fileDir);
    }

    @FXML
    public int doStuff() throws Exception {
        File dbFile = new File("DBName.txt");
        BufferedReader br = new BufferedReader(new FileReader(dbFile));
        if (dbFile.exists()) {
            dbName = br.readLine();
        } else {
            dbName = null;
        }

        if (fileDir == "~/") {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Open database? " +"\n Path to database has not been selected", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.NO) {
                return 0;
            }
        }

        if (dbName == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Open database? " +"\n Name for database has not been selected", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                dbName = "myDB";
            } else if (alert.getResult() == ButtonType.NO) {
                return 0;
            }
        }

        try {
            // String dbPath = "jdbc:h2:~/Desktop/myDB/myDB";
            File file = new File("DBName.txt");
            BufferedReader bufferedReadereader = new BufferedReader(new FileReader(file));
            String dbName = bufferedReadereader.readLine();
            String dbPath = fileDir + "/" + dbName;
            String scriptPath = "src/main/resources/com/database/db.sql";

            Connection connection = DriverManager.getConnection("jdbc:" + "h2:" + dbPath);
            FileReader reader = new FileReader(scriptPath);
            RunScript.execute(connection, reader);
            Statement s = connection.createStatement();
            ResultSet resultset = s.executeQuery("Show columns from mytable");
            while (resultset.next()) {
                System.out.println(resultset.getString(1));
            }
            System.out.print("connectin  is valid : " + connection.isValid(20));
        } catch (Exception e) {e.printStackTrace();}
        return 0;
    }

    @FXML
    public void createDB() {
        TextInputDialog dialog = new TextInputDialog("Name");
        dialog.setTitle("Input Name for new Database");
        dialog.setHeaderText("New Database Needs a Name");
        dialog.setContentText("Please enter name");
        dialog.showAndWait();

        dbName = dialog.getResult();
        System.out.println(dbName);

        File file = new File("DBName.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(dbName);
            writer.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
