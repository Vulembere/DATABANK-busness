package cls;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.prefs.Preferences;

/**
 *
 * @author SMART PC
 */
public class Pref {

    public Preferences prefs = Preferences.userNodeForPackage(Pref.class);
    String tva;
    String taux;
    String Serveur;
    String Mot_de_Pass;
    String User;
    String Database;
    String DeFaultChaimain;

    public void SetDevise(String values) {
        this.prefs.put("devise", values);
    }

    public String getDevise() {
        return this.prefs.get("devise", "");
    }

    public void SetDeFaultChaimain(String values) {
        this.prefs.put("chemain", values);
    }

    public String getDeFaultChaimain() {
        return this.prefs.get("chemain", "C:\\Users");
    }

    public void SetTVA(Double values) {
        this.prefs.putDouble("tva", values);
    }

    public String getDatabase() {
        return this.prefs.get("DB", "");
    }

    public void setDatabase(String Database) {
        this.prefs.put("DB", Database);
    }

    public String getServeur() {
        return this.prefs.get("Serveur", "");
    }

    public void setServeur(String Serveur) {
        this.prefs.put("Serveur", Serveur);
    }

    public String getMot_de_Pass() {
        return this.prefs.get("Mot_De_passe", "");
    }

    public void setMot_de_Pass(String Mot_de_Pass) {
        this.prefs.put("Mot_De_passe", Mot_de_Pass);
    }

    public String getUser() {
        return this.prefs.get("User", "");
    }

    public void setUser(String User) {
        this.prefs.put("User", User);
    }

    public Double getTVA() {
        return this.prefs.getDouble("tva", 0.16);
    }

    public void SetTAUX(Double value) {
        this.prefs.putDouble("taux", value);
    }

    public Double getTAUX() {
        return this.prefs.getDouble("taux", 1600.0);
    }

    public String getLinkFile() {
        return this.prefs.get("file", "");
    }

    public void SetLinkFile(String file) {
        this.prefs.put("file", file);
    }
    public static Pref pref = new Pref();

    ///////////////////////////////////////////////////////////////////////////////
    //////////////////////////////Mes reqetes//////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
}
