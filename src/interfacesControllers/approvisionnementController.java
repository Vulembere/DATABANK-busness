/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesControllers;

import chargementControllers.LoadDetailleApprovController;
import chargementControllers.loadArticleApprovController;
import cls.evenements;
import static cls.evenements.ev;
import static cls.references.*;
import com.jfoenix.controls.JFXButton;
import dbo.Querry;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.textfield.TextFields;
import traitement.AutoCompress;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class approvisionnementController implements Initializable {

    @FXML
    private TextField txtRechercher;
    private Text txtNombre;
    @FXML
    private VBox vbx;
    ArrayList<String> Designation = new ArrayList();
    ArrayList<String> Infos = new ArrayList();
    public ArrayList<String> ARTICLE = new ArrayList();
    public ArrayList<Integer> QTE = new ArrayList();
    public ArrayList<Float> PA = new ArrayList();
    @FXML
    private AnchorPane panFournnssr;
    @FXML
    private VBox vbApprov;
    public static VBox newVBOX;
    TextField txtFournisseur = TextFields.createClearableTextField();
    @FXML
    private Text txtCodeApprov;
    @FXML
    private JFXButton btn_save;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private JFXButton btnPrint;
    @FXML
    private TextArea txtObserv;
    @FXML
    private FontAwesomeIconView font;
    @FXML
    private Label labInfos;
    @FXML
    private DatePicker txtDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCodeApprov.setText(String.valueOf(Querry.getLastID("approv") + 1));
        newVBOX = vbApprov;
        AutoCompress.Autocomplete(txtFournisseur, panFournnssr);
        AutoCompress.ChargememtCompression(txtFournisseur, "fournisseur", "Nom");
        try {

            Scrool(vbApprov, null, 0, (float) 0);
            load(Querry.getResult("select * from v_article order by article"));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        aprov.ARTICLE.clear();
        aprov.QTE.clear();
        aprov.PA.clear();
    }

    void load(ResultSet rs) throws SQLException, IOException {
        init();
        while (rs.next()) {
            Designation.add(rs.getString(2));
            Infos.add("Qte : " + rs.getString(5) + " " + rs.getString(7) + " , PV Gros : " + rs.getString(8) + " , PV Det : " + rs.getString(9));
        }
        int x = 0;
        AnchorPane[] node = new AnchorPane[Designation.size()];
        vbx.getChildren().clear();
        while (x < Designation.size()) {
            loadArticleApprovController.Nom = Designation.get(x);
            loadArticleApprovController.Infos = Infos.get(x);
            node[x] = FXMLLoader.load(getClass().getResource("/chargement/loadArticleApprov.fxml"));
            vbx.getChildren().add(node[x]);
            x++;
        }
    }

    void init() {
        Designation.clear();
        Infos.clear();
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException, IOException {
        load(Querry.getResult("select * from v_article where article like '%" + txtRechercher.getText().toUpperCase() + "%' or Categorie like '%" + txtRechercher.getText().toUpperCase() + "%'"));
    }

    @FXML
    private void NouveauArticle(ActionEvent event) throws IOException {
        ev.showFormDialog(AJOUT_FOURNISSEUR);
    }

    @FXML
    private void save(ActionEvent event) throws IOException, SQLException {
        int x = 0;
        String codeApprov = Querry.getValueInt("call newApprovision('" + txtFournisseur.getText() + "','" + txtObserv.getText() + "','" + var.getCodeUser() + "')");
        System.err.println("$### " + codeApprov);
        System.err.println("$### Size" + aprov.ARTICLE.size());
        if (codeApprov.equals("notexist")) {
            ev.showMssge(labInfos, font, "Ce Fournisseur n'existe pas !", 0);
        } else {
            if (Integer.parseInt(codeApprov) > 0) {
                if (aprov.ARTICLE.size() > 0) {
                    if (checkQte()) {
                        while (x < aprov.ARTICLE.size()) {
                            if (Querry.execute("call newDetailleApprov('" + aprov.ARTICLE.get(x) + "','" + aprov.QTE.get(x) + "','" + aprov.PA.get(x) + "','" + codeApprov + "')")) {
                                ev.showMssge(labInfos, font, "Enregistrement reussie  !", 1);
                                cool = true;
                            } else {
                                ev.showMssge(labInfos, font, "Echec d'enregistrement !", 0);
                            }
                            x++;
                        }
                        if (cool) {
                            clean();
                        }
                    } else {
                        ev.showMssge(labInfos, font, "Vous pouvez pas enregistrer des produit avec 0 comme Qte. !", 0);
                    }

                } else {
                    ev.showMssge(labInfos, font, "Aucune donnée n'as ete prévue !", 0);
                }
            }
        }
    }
    boolean cool;

    void clean() throws IOException, SQLException {
        evenements.initFields(txtFournisseur, txtObserv);
        txtFournisseur.setText("Aucun");
        txtObserv.setText("Aucune");
        aprov.ARTICLE.clear();
        aprov.QTE.clear();
        aprov.PA.clear();
        aprov.Scrool(vbApprov, null, 0, (float) 0);
        load(Querry.getResult("select * from v_article order by article"));
        txtCodeApprov.setText(String.valueOf(Querry.getLastID("approv") + 1));
    }

    @FXML
    private void delete(ActionEvent event) throws IOException, SQLException {
        clean();
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }

    public void Scrool(VBox vbx, String Article, int Qte, float Pa) throws IOException {
        if (Article != null) {
            if (ARTICLE.contains(Article)) {
                int index = ARTICLE.indexOf(Article);
                QTE.set(index, Qte);
                PA.set(index, Pa);
            } else {
                ARTICLE.add(Article);
                QTE.add(0);
                PA.add((float) 0);
            }
        }
        load(vbx);
    }

    public void load(VBox vbx) throws IOException {
        int i = 0;
        AnchorPane[] node = new AnchorPane[ARTICLE.size()];
        vbx.getChildren().clear();
        while (i < ARTICLE.size()) {
            LoadDetailleApprovController.Article = ARTICLE.get(i);
            LoadDetailleApprovController.Numero = String.valueOf(i + 1);
            LoadDetailleApprovController.Qte = QTE.get(i).toString();
            LoadDetailleApprovController.Pa = PA.get(i).toString();
            node[i] = FXMLLoader.load(getClass().getResource("/chargement/loadDetailleApprov.fxml"));
            vbx.getChildren().add(node[i]);
            i++;
        }

    }

    public void remove(String Article) {
        if (ARTICLE.contains(Article)) {
            int index = ARTICLE.indexOf(Article);
            ARTICLE.remove(index);
            QTE.remove(index);
            PA.remove(index);
        }
    }
    public static approvisionnementController aprov = new approvisionnementController();

    @FXML
    private void AfficherApprovisionnement(ActionEvent event) throws SQLException, IOException {
        aprov.ARTICLE.clear();
        aprov.QTE.clear();
        aprov.PA.clear();
        var.setLoad(false);
        ResultSet rs = Querry.getResult("SELECT * FROM `v_approv` where date='" + txtDate.getValue() + "'");
        while (rs.next()) {
            var.setLoad(true);
            txtCodeApprov.setText(rs.getString(1));
            txtFournisseur.setText(rs.getString(2));
            txtObserv.setText(rs.getString(3));

            aprov.ARTICLE.add(rs.getString(5));
            aprov.QTE.add(rs.getInt(7));
            aprov.PA.add(rs.getFloat(8));
        }
        aprov.load(vbApprov);
        var.setLoad(false);
    }

    public boolean checkQte() {
        int i = 0;
        boolean bool = true;
        while (i < aprov.ARTICLE.size()) {
            if (aprov.QTE.get(i) <= 0) {
                bool = false;
            }
            i++;
        }
        return bool;
    }
}
