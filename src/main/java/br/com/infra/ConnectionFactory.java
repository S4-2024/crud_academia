package br.com.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private ConnectionFactory() {}

    public static Connection getConnection() {

        try {
           return  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/academia", "root", "admin");
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
