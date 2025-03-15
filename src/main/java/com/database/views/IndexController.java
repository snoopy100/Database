package com.database.views;

import org.h2.tools.*;

import java.io.*;
import java.sql.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.template.Id;


@Route("")
@Tag("index")
public class IndexController extends LitTemplate{
    @Id("button")
    public Button button;

    public IndexController() {
        button.addClickListener( e -> {
            try {
                open();
            } catch(Exception ex) {ex.printStackTrace();}
        });
        System.out.println(button.getText());
    }

    public int open() throws Exception {
        System.out.println("yippee");
        try {
            String dbPath = "jdbc:h2:~/Desktop/myDB/myDB";
            String scriptPath = "src/main/resources/com/database/db.sql";

            Connection connection = DriverManager.getConnection("jdbc:" + "h2:" + dbPath);
            FileReader reader = new FileReader(scriptPath);
            RunScript.execute(connection, reader);
            Statement s = connection.createStatement();
            ResultSet resultset = s.executeQuery("Show columns from mytable");
            while (resultset.next()) {
                System.out.println(resultset.getString(1));
            }
            System.out.println("connectin  is valid : " + connection.isValid(20));
        } catch (Exception e) {e.printStackTrace();}

        return 0;
    }
}
