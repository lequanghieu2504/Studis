package com.studis.utils.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.sudvsxblwhrvygkdlxio&password=password";
        
        try {
            Class.forName("org.postgresql.Driver");
            
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            return null;
        }
    }
}
