/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import cls.evenements;
import java.sql.*;

/**
 *
 * @author wills
 */
public class connexion {

    public static Connection con;
    public static PreparedStatement pst;
    public static ResultSet rs;
    private static final String password = "";
    private static final String user = "root";
    private static final String BD = "databankbusness";
    private static final String hot = "localhost";
    private static final String port = "3306";
    private static final String url = "jdbc:mysql://" + hot + ":" + port + "/" + BD;

    public static Connection Con() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Correct");
            } catch (ClassNotFoundException | SQLException ex) {
                evenements.ev.alerteInfos(ex.getMessage(), false);
            }
        }
        return con;
    }
}
