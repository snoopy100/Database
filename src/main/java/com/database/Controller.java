package com.database;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.h2.tools.RunScript;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML HBox bar;
    @FXML Button button;

    @FXML
    public void doStuff() {
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
