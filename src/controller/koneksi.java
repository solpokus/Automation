package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {

    private static Connection mysqlCon;

    public static Connection connDb() throws SQLException {
        if (mysqlCon == null) {
            try {
                String db = "jdbc:mysql://localhost:3306/histpa1";
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlCon = (Connection) DriverManager.getConnection(db,user,pass);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Connection "+e);
            }
        }
        return mysqlCon;
    }
}
