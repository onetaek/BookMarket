package shop.bookmarket.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        String url ="jdbc:mariadb://localhost:3310/book_market";
        String user ="root";
        String password = "7517";
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }
}
