package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static final String SERVER = getConfig("DB_SERVER", "localhost:1433");
    private static final String DATABASE = getConfig("DB_NAME", "PRJ301_CarWashManagement");
    private static final String USER = getConfig("DB_USER", "carwash_management");
    private static final String PASSWORD = getConfig("DB_PASSWORD", "group7");

    private static final String URL = "jdbc:sqlserver://" + SERVER
            + ";databaseName=" + DATABASE
            + ";encrypt=true"
            + ";trustServerCertificate=true";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
        }
    }

    private static String getConfig(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            value = System.getProperty(key);
        }
        return value == null || value.trim().isEmpty() ? defaultValue : value.trim();
    }
}
