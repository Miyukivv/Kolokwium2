package com.example.demo;

import org.springframework.cglib.core.Local;

import java.sql.*;
import java.time.LocalDateTime;

public class Database {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:pixel.db";

    private Connection conn;
    private Statement stat;
    private static Database instance;

    private Database() {
        connectionOfDatabase();
        createTable();
    }

    private void connectionOfDatabase() {

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (
                SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public static Database getInstance(){
        if (instance==null){
            instance=new Database();
        }
        return instance;
    }

    private void createTable(){
        String createMyTable = "CREATE TABLE IF NOT EXISTS entry (token INTEGER NOT NULL, x INTEGER NOT NULL, y INTEGER NOT NULL, color TEXT NOT NULL, timestamp TEXT NOT NULL);";
        try {
            stat.execute(createMyTable);

        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }

    /* PreparedStatement prepStmt = conn.prepareStatement(
                      "insert into czytelnicy values (NULL, ?, ?, ?);");
              prepStmt.setString(1, imie);
              prepStmt.setString(2, nazwisko);
              prepStmt.setString(3, pesel);
              prepStmt.execute();*/

    public void addPixelToDatabase(int id, int x, int y, String hexcolor){
        String insert="INSERT INTO entry (token, x, y, color, timestamp) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement(insert);
            prst.setInt(1,id);
            prst.setInt(2,x);
            prst.setInt(3,y);
            prst.setString(4,hexcolor);
            prst.setString(5, LocalDateTime.now().toString());
            prst.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    public void deletePixelFromDatabase(int id, int x, int y, String hexcolor){
//        String delete="DELETE FROM entry WHERE token=?;\n";
//    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}
