/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DialogController;

import cls.evenements;
import static cls.evenements.ev;
import com.jfoenix.controls.JFXButton;
import dbo.Querry;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class AddArticleController implements Initializable {
    
    @FXML
    private Text title;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private ComboBox<String> txtCategorie;
    @FXML
    private JFXButton btn_AddCAteg;
    @FXML
    private TextField txtDesignation;
    @FXML
    private ComboBox<String> txtType;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtQteMin;
    @FXML
    private TextField txtChemainImage;
    @FXML
    private JFXButton btAdd1;
    @FXML
    private FontAwesomeIconView font;
    @FXML
    private Label labInfos;
    @FXML
    private ComboBox<String> txtMesure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var.LoadType(txtType);
        var.LoadUniteMesure(txtMesure);
        btn_delete.setDisable(true);
        if (var.getDesignation() != null) {
            try {
                ResultSet rs = Querry.getResult("select * from v_article where article='" + var.getDesignation() + "'");
                while (rs.next()) {
                    codeArticle = rs.getInt(1);
                    txtDesignation.setText(rs.getString(2));
                    txtType.getSelectionModel().select(rs.getString(3));
                    txtCategorie.getSelectionModel().select(rs.getString(4));
                    txtQuantite.setText(rs.getString(5));
                    txtQteMin.setText(rs.getString(6));
                    txtMesure.getSelectionModel().select(rs.getString(7));
                    btn_delete.setDisable(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        var.setRs(Querry.getResult("call deleteArticle('" + codeArticle + "')"));
        if (var.getRs().next()) {
            if (var.getRs().getString(1).equals("error")) {
                ev.showMssge(labInfos, font, "Suppression impossible car cet article a deja été utiliser dans le systeme  !", 0);
            } else if (var.getRs().getString(1).equals("succed")) {
                ev.showMssge(labInfos, font, "Traitement reusie ! !", 1);
                evenements.initFields(txtCategorie, txtDesignation, txtQteMin, txtType, txtMesure, txtChemainImage);
            }
        }
    }
    
    @FXML
    private void AddCategorie(ActionEvent event) {
        txtCategorie.setEditable(true);
    }
    
    @FXML
    private void AddType(ActionEvent event) {
        txtType.setEditable(true);
    }
    int codeArticle;
    
    @FXML
    private void Save(ActionEvent event) throws SQLException {
        if (!evenements.isFieldsempty(txtCategorie, txtDesignation, txtQteMin, txtType, txtMesure)) {
            if (ev.isNumber(txtQteMin.getText())) {
                var.setRs(Querry.getResult("call AjoutArticle('" + txtDesignation.getText().toUpperCase() + "','" + txtType.getValue().toUpperCase() + "','" + txtCategorie.getValue().toUpperCase() + "','" + txtQteMin.getText() + "','','" + codeArticle + "','" + txtMesure.getValue() + "')"));
                if (var.getRs().next()) {
                    if (var.getRs().getString(1).equals("exist")) {
                        ev.showMssge(labInfos, font, "Un article avec ce meme nom existe deja  !", 0);
                    } else {
                        ev.showMssge(labInfos, font, "Traitement reusie ! !", 1);
                        evenements.initFields(txtCategorie, txtDesignation, txtQteMin, txtType, txtMesure, txtChemainImage);
                    }
                }
            } else {
                ev.showMssge(labInfos, font, "Votre Quantité mainimale est incorret !", 0);
            }
        } else {
            ev.showMssge(labInfos, font, "Les champs Designation, Catégorie, Type, QteMin et l'unité de mesure sont Obligatoire !", 0);
        }
    }
    
    @FXML
    private void chargerCateg(ActionEvent event) {
        var.LoadCategorie(txtType.getValue().toUpperCase(), txtCategorie);
    }
    
}
