package com.studis.utils.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Utility class to manage database connections to a PostgreSQL database.
 * It provides a method to establish a connection using the connection URL.
 */
public class ConnectionManager {

    /**
     * Establishes a connection to the PostgreSQL database using the provided URL and credentials.
     * 
     * @return A Connection object if the connection is successful, or null if the connection fails.
     */
    public static Connection getConnection() {
        // Connection string containing the database URL, username, and password
        String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.sudvsxblwhrvygkdlxio&password=password";

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish and return the connection
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            // Return null if connection fails (e.g., incorrect URL or credentials)
            return null;
        }
    }
}
