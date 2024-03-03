package com.database.Services;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MySQLConnection{
    private static String connectionString = "jdbc:mysql://localhost:3306/Drones";
    private static String user = "root";
    private static String password = "superduke1290";
    private static String host = "localhost";
    private static int port = 3306;
    private static String database ="test_products";

    public static Connection getConnection() throws SQLException{
        return getDatasource().getConnection();
    }

    public  static DataSource getDatasource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(connectionString);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setInitialSize(2);
        return ds;
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement statement) throws SQLException {
        statement.close();
    }

    public static void close(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    public static void close(Connection cnn) throws SQLException {
        cnn.close();
    }

    public static void setDatabaseUser(String userdb) {
        user = userdb;
    }

    public static void setDatabasePassword(String passwd) {
        password = passwd;
    }

    public static void reloadStringConnection() {
        connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database +
                "?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MySQLConnection.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        MySQLConnection.port = port;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        MySQLConnection.database = database;
    }

    public static String getConnectionString() {
        return connectionString;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

}
