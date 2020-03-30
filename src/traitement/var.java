/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import dbo.Querry;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.scene.control.ComboBox;

/**
 *
 * @author wills
 */
public class var extends Querry {

    public static ResultSet rsS;
    public static int CodeArticle;
    public static String Designation;
    public static int codeUser = 1;
    public static Boolean load;

    public static Boolean getLoad() {
        return load;
    }

    public static void setLoad(Boolean load) {
        var.load = load;
    }

    public static int getCodeUser() {
        return codeUser;
    }

    public static void setCodeUser(int codeUser) {
        var.codeUser = codeUser;
    }

    public static String getDesignation() {
        return Designation;
    }

    public static void setDesignation(String Designation) {
        var.Designation = Designation;
    }

    public static int getCodeArticle() {
        return CodeArticle;
    }

    public static void setCodeArticle(int CodeArticle) {
        var.CodeArticle = CodeArticle;
    }

    public static ResultSet getRs() {
        return rsS;
    }

    public static void setRs(ResultSet rs) {
        rsS = rs;
    }

    public static String getNewBarcode() {
        int Year = LocalDate.now().getYear();
        int Moth = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        int Seconde = LocalDateTime.now().getSecond();
        int Heur = LocalDateTime.now().getHour();
        int Munites = LocalDateTime.now().getMinute();

        return (Seconde + "" + Munites + "" + Heur + "" + day + "" + Moth+"0"+var.getCodeUser()).substring(0,10)+""+String.valueOf(Year).substring(2,4);

    }

    public static void LoadUniteMesure(ComboBox box) {
        box.getItems().add("Litre");
        box.getItems().add("Mètre");
        box.getItems().add("Kg");
        box.getItems().add("Pièce");
    }

    public static void LoadType(ComboBox box) {
        ChargerCombo(box, "select designation from type order by designation", "designation");
    }

    public static void LoadCategorie(String Type, ComboBox box) {
        ChargerCombo(box, "select Categorie from v_categorie where type='" + Type + "' order by Categorie", "Categorie");
    }
    public static void LoadZone(ComboBox box) {
        ChargerCombo(box,"select designation from zone order by designation", "designation");
    }

}
