package com.database;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.h2.tools.RunScript;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    @FXML HBox bar;
    @FXML Button openButton;
    @FXML Button selectButton;
    @FXML Button createButton;
    @FXML AnchorPane pane;
    @FXML Button changeButton;

    DirectoryChooser chooser;
    String fileDir = "~/Desktop";
    String dbName = "myDB";
    public String dbPath;

    public void goToField(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("Field.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void select(Event e) {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(pane.getScene().getWindow());

        fileDir = file.getAbsolutePath();
        System.out.println(fileDir);
    }

    @FXML
    public int open(ActionEvent actionEvent) throws Exception {
        try {
            // String dbPath = "jdbc:h2:~/Desktop/myDB/myDB";

            File file = new File("dbName.txt");
            BufferedReader bufferedReadereader = new BufferedReader(new FileReader(file));
            this.dbPath = fileDir + "/" + dbName;
            String scriptPath = "src/main/resources/com/database/db.sql";

            Connection connection = DriverManager.getConnection("jdbc:h2:" + dbPath);
            FileReader reader = new FileReader(scriptPath);
            RunScript.execute(connection, reader);
            Statement s = connection.createStatement();
            ResultSet resultset = s.executeQuery("Show columns from mytable");
            while (resultset.next()) {
                System.out.println(resultset.getString(1));
            }
            System.out.println("connectin  is valid : " + connection.isValid(20));
        } catch (Exception e) {e.printStackTrace();}

        File file = new File("dbName.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(dbPath);
            writer.close();
        } catch (IOException e) {e.printStackTrace();}

        goToField(actionEvent);
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
