package br.feevale.connection;

import java.net.URI;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null){
            try {
                Class.forName("org.postgresql.Driver");

                URI dbUri = new URI(System.getenv("DATABASE_URL"));
                String sslMode = System.getenv("SSL_MODE");

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];

                String dbUrl = "jdbc:postgresql://" +
                        dbUri.getHost() + ':' +
                        dbUri.getPort() + dbUri.getPath();

                Properties props = new Properties();
                props.setProperty("user", username);
                props.setProperty("password", password);
                props.setProperty("ssl", sslMode);
                connection = DriverManager.getConnection(dbUrl, props);
            } catch (Exception e) {
                System.out.println("==================>" + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
