package controller;


import Encaps.EnConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {

    private static Connection mysqlCon;

    public static Connection connDb() throws SQLException {
        EnConn enCon = new EnConn();
        if (mysqlCon == null) {
            try {
                enCon.setDb("jdbc:mysql://localhost:3306/histpa1");
                enCon.setUser("root");
                enCon.setPass("");
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlCon = (Connection) DriverManager.getConnection(enCon.getDb(),enCon.getUser(),enCon.getPass());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Connection "+e);
            }
        }
        return mysqlCon;
    }
}
