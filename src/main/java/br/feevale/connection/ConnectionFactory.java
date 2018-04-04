package br.feevale.connection;

import java.net.URI;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            ConnectionFactory.logger("DATABASE_URL=>" + System.getenv("DATABASE_URL"));
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];

            String dbUrl = "jdbc:postgresql://" +
                    dbUri.getHost() + ':' +
                    dbUri.getPort() + dbUri.getPath();
            ConnectionFactory.logger("dbUrl=>" + dbUrl);

            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("ssl", "true");

            ConnectionFactory.logger("props=>" + props);

            return DriverManager.getConnection(dbUrl, props);
//            return DriverManager.getConnection(dbUrl, username, password);
//            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/pdnews", "postgres", "root");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void logger(String log){
        System.out.println("-----------");
        System.out.println(log);
        System.out.println("-----------");
    }
}
