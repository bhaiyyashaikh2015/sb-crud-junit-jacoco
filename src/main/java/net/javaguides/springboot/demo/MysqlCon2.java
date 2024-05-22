package net.javaguides.springboot.demo;

import net.javaguides.springboot.entity.User;

import java.sql.*;

public class MysqlCon2 {

    public static void main(String args[]) {
        System.out.println("User ==> "+getUser());
    }

    public static User getUser() {
        User user = new User();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user_management", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where id=2");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                con.close();
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

}
