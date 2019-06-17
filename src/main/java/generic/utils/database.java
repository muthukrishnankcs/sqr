package generic.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface database {

    String url = "jdbc:postgresql://172.17.1.222/postgres";
    String driverName = "org.postgresql.Driver";
    String username = "sqr";
    String password = "sqr";

    Connection getConnection() throws SQLException;
    ResultSet getResultSet(String query) throws SQLException;
    void closeConnection() throws SQLException;
}
