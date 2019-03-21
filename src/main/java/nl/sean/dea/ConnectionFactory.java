package nl.sean.dea;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class ConnectionFactory {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/spotitube?useSSL=false&serverTimezone=UTC";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "spotitube";

    private Properties properties;

    public ConnectionFactory() {
        properties = getProperties();
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String propertiesPath = getClass().getClassLoader().getResource("").getPath() + "database.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            properties.setProperty("db.url", MYSQL_URL);
            properties.setProperty("db.user", MYSQL_USER);
            properties.setProperty("db.password", MYSQL_PASSWORD);
            properties.setProperty("db.driver", MYSQL_DRIVER);
            e.printStackTrace();
        }
        return properties;
    }

    public Connection getConnection() {
        loadDriver();
        try {
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
