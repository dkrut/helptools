package org.bitbucket.dkrut.Tools.DataBase;

import java.sql.*;

/**
 * Created by Denis Krutikov on 08.01.2019.
 */

public class DBTool {

    public void mssql(String query){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://SERVER:PORT;DatabaseName=DB_NAME";
            String login = "LOGIN";
            String password = "PASSWORD";

            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public void pg(String query){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://SERVER:PORT/DB_NAME";
            String login = "LOGIN";
            String password = "PASSWORD";

            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public String getValueMssql (String query, String columnName){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://SERVER:PORT;DatabaseName=DB_NAME";
            String login = "LOGIN";
            String password = "PASSWORD";

            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    return result.getString(columnName);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }

        return null;
    }

    public String getValuePg (String query, String columnName){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://SERVER:PORT/DB_NAME";
            String login = "LOGIN";
            String password = "PASSWORD";

            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    return result.getString(columnName);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }

        return null;
    }
}