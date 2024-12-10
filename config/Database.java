package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String dbName;
    private final String userName;
    private final String password;
    private final String host;
    private final String port;
    private Connection connection;

    // Constructor untuk konfigurasi database
    public Database(final String dbName, final String userName, final String password, final String host, final String port) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    // Getter untuk mendapatkan koneksi
    public Connection getConnection() {
        return connection;
    }

    // Metode untuk setup koneksi database
    public void setup() {
        String mysqlConnUrlTemplate = "jdbc:mysql://%s:%s/%s";
        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            connection = DriverManager.getConnection(
                    String.format(mysqlConnUrlTemplate, host, port, dbName),
                    userName,
                    password
            );
            System.out.println("Database connected!");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection failed!");
            throw new RuntimeException(e);
        }
    }
}
