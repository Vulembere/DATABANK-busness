/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import cls.evenements;
import static cls.evenements.ev;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;

/**
 *
 * @author wills
 */
public class Querry extends connexion {

    public static ResultSet getResult(String rqt) throws SQLException {
        pst = Con().prepareStatement(rqt);
        return pst.executeQuery();
    }

    public static boolean execute(String rqt) {
        boolean bool = false;
        try {
            pst = Con().prepareStatement(rqt);
            pst.execute();
            bool = true;
        } catch (SQLException ex) {
            Logger.getLogger(Querry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    public static void ChargerCombo(ComboBox Box, String rqt, String Colone) {
        Box.getItems().clear();
        try {
            pst = Con().prepareStatement(rqt);
            rs = pst.executeQuery();
            while (rs.next()) {
                Box.getItems().add(evenements.Premier_Maj(rs.getString(Colone)));
            }
        } catch (SQLException ex) {
            ev.alerteInfos("## " + ex.getMessage(), true);
        }
    }

    public static String getValueInt(String rqt) {
        String value = null;
        try {
            pst = Con().prepareStatement(rqt);
            rs = pst.executeQuery();
            while (rs.next()) {
                value = rs.getString(1);
            }
        } catch (SQLException ex) {
            ev.alerteInfos("## " + ex.getMessage(), true);
        }
        return value;
    }

    public static int getLastID(String table) {
        int value = 0;
        try {
            pst = Con().prepareStatement("select ifnull(max(code),0) as code from " + table + "");
            rs = pst.executeQuery();
            while (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ev.alerteInfos("## " + ex.getMessage(), true);
        }
        return value;
    }
}
