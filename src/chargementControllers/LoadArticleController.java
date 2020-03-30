/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chargementControllers;

import static cls.evenements.ev;
import static cls.references.AJOUT_ARTICLE;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class LoadArticleController implements Initializable {

    @FXML
    private Label txtNom;
    @FXML
    private Label txtInfos;
    @FXML
    private FontAwesomeIconView btn_afficher;
    @FXML
    private Label txtIndice;
    public static String Nom;
    public static String Infos;
    public static int Qte;
    public static int QteMin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtInfos.setText(Infos);
        txtNom.setText(Nom);
        if (!txtNom.getText().contains(" ")) {
            txtIndice.setText(txtNom.getText().substring(0, 1).toUpperCase());
        } else {
            txtIndice.setText(txtNom.getText().substring(0, 1) + "" + txtNom.getText().substring(txtNom.getText().indexOf(" ") + 1, txtNom.getText().indexOf(" ") + 1).toUpperCase());
        }
        if (Qte <= QteMin) {
            txtIndice.setTextFill(Color.RED);
        } else {
            txtIndice.setTextFill(Color.WHITE);
        }
    }

    @FXML
    private void Afficher(MouseEvent event) throws IOException {
        var.setDesignation(txtNom.getText());
        ev.showFormDialog(AJOUT_ARTICLE);
        var.setDesignation(null);
    }

}
