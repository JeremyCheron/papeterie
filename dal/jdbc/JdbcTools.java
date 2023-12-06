package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.papeterie.dal.Settings;


public class JdbcTools {

    private static String urldb;
    private static String userdb;
    private static String passworddb;

    static {
        try {
            Class.forName(Settings.getProperty("driverdb"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        urldb = Settings.getProperty("urldb");
        userdb = Settings.getProperty("userdb");
        passworddb = Settings.getProperty("passworddb");
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(urldb, userdb, passworddb);

        return connection;
    }

    public static PreparedStatement preparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    public static Statement createStatement() throws SQLException {
        return getConnection().createStatement();
    }

    
}
