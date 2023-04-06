package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfigConnection {
    private Connection getConnection() throws SQLException {
        final String user = "postgres";
        final String password = "Vstupinfo0108@";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        return statement;
    }
}
