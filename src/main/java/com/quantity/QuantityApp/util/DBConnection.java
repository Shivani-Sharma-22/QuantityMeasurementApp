package com.quantity.QuantityApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.h2.tools.Server;

public class DBConnection {

    // Use in-memory DB to avoid file lock issues during dev/testing
    private static final String URL = "jdbc:h2:~/testdb;AUTO_SERVER=TRUE"; 
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Server webServer;

    static {
        try {
            // Start H2 console only if not in test environment
            if (System.getProperty("test.env") == null) {
                try {
                    webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8085").start();
                    System.out.println("H2 Console started at http://localhost:8085");
                } catch (Exception e) {
                    System.out.println("H2 Console may already be running on 8085");
                }
            }

            // Initialize DB
            initializeDatabase();
            System.out.println("H2 Database initialized");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get JDBC connection
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create table if not exists
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS quantity_measurements (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "operation VARCHAR(50)," +
                    "operand1 VARCHAR(100)," +
                    "operand2 VARCHAR(100)," +
                    "result VARCHAR(100)," +
                    "error VARCHAR(255))";

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Optional: stop console when app stops
    public static void stopConsole() {
        if (webServer != null) {
            webServer.stop();
            System.out.println("H2 Console stopped");
        }
    }
}