package net.javaguides.springboot.demo;

import java.sql.*;

public class MysqlCon {

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user_management", "root", "root");
            ResultSet rs = getResultSet(con, "select * from users");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ResultSet getResultSet(Connection con, String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
}
