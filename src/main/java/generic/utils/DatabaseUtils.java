package generic.utils;

import org.apache.log4j.Logger;

import java.sql.*;

public class DatabaseUtils implements database{

    private static Connection con;
    private static ResultSet rs;
    private static PreparedStatement st;

    Logger logs = Logger.getLogger("devpinoyLogger");

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            logs.error("Driver not found.");
        }
        con = DriverManager.getConnection(url, username, password);
        logs.info("Database Connection established successfully ");
        return con;
    }

    public ResultSet getResultSet(String query) throws SQLException {
        st = con.prepareStatement(query);
        rs = st.executeQuery();
        return rs;
    }

    public int updateResultSet(String query) throws SQLException {
        st = con.prepareStatement(query);
        return st.executeUpdate();
    }

    public void closeConnection() throws SQLException
    {
        con.close();
    }

}
