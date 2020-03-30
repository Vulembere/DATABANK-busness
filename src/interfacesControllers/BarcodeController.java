/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesControllers;

import chargementControllers.loadArticleApprovController;
import chargementControllers.loadArticleBarcodeController;
import cls.evenements;
import static cls.evenements.ev;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class BarcodeController implements Initializable {

    ArrayList<String> Designation = new ArrayList();
    ArrayList<String> Infos = new ArrayList();
    @FXML
    private Text txtBarcode;
    @FXML
    private ComboBox<?> txtZone;
    @FXML
    private VBox vbApprov;
    @FXML
    private FontAwesomeIconView font;
    @FXML
    private Label labInfos;
    @FXML
    private Label txtNom;
    @FXML
    private Label txtInfos;
    @FXML
    private TextField txtQuantite;
    @FXML
    private JFXButton btn_save;
    @FXML
    private VBox vbx;
    @FXML
    private TextField txtRechercher;
    public static Text newBarcode;
    public static Label newArticle;
    public static Label newInfos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newBarcode = txtBarcode;
        newArticle = txtNom;
        newInfos = txtInfos;
        var.LoadZone(txtZone);
        try {
            load(Querry.getResult("select * from v_article order by article"));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(BarcodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void produire(ActionEvent event) throws SQLException {
        if (!evenements.isFieldsempty(txtQuantite, txtZone)) {
            if (ev.isNumber(txtQuantite.getText())) {
                if (Integer.parseInt(txtQuantite.getText()) > 0) {
                    ResultSet rs = Querry.getResult("call ApprovZone('" + txtNom.getText() + "','" + txtQuantite.getText() + "','" + txtBarcode.getText() + "','" + txtZone.getValue() + "','" + var.getCodeUser() + "')");
                    while (rs.next()) {
                        System.err.println("# Qte : "+rs.getInt(2)+"# Barcode : "+rs.getLong(1));
                        if (rs.getLong(1) != 0) {
                            txtBarcode.setText("" + rs.getLong(1));
                            ev.showMssge(labInfos, font, "Traitement effecté avec succè !", 1);
                        } else if (rs.getInt(2) >= 0) {
                            if (rs.getInt(2) == 0) {
                                ev.showMssge(labInfos, font, "Veiller approvionner cet Article Avant de continuer !", 0);
                            } else{
                                ev.showMssge(labInfos, font, "Vous de pouvez produire Que " + rs.getInt(2) + " en Quantité !", 0);
                            }
                        }
                    }
                } else {
                    ev.showMssge(labInfos, font, "Votre Quantité doit etre supérieur a 0 !", 0);
                }
            } else {
                ev.showMssge(labInfos, font, "Votre Quantité est incorrect !", 0);
            }
        } else {
            ev.showMssge(labInfos, font, "Les chmps Zone et la Quantité sont obligatoire !", 0);
        }
    }

    @FXML
    private void rechercher(KeyEvent event) {
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
            loadArticleBarcodeController.Nom = Designation.get(x);
            loadArticleBarcodeController.Infos = Infos.get(x);
            node[x] = FXMLLoader.load(getClass().getResource("/chargement/loadArticleBarcode.fxml"));
            vbx.getChildren().add(node[x]);
            x++;
        }
    }

    void init() {
        Designation.clear();
        Infos.clear();
    }

    @FXML
    private void AddType(ActionEvent event) {
        txtZone.setEditable(true);
    }
}
