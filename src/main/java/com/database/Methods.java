package com.database;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.h2.tools.RunScript;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Methods {

    public static void dropTable(String tableName, String dbPath) {
        try {
            Connection connection = DriverManager.getConnection(dbPath);
            Statement s = connection.createStatement();
            s.executeUpdate("DROP TABLE " + tableName);
        } catch (Exception e) {e.printStackTrace();}
    }

    public static void runCommands(String[] commands) {
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
}
