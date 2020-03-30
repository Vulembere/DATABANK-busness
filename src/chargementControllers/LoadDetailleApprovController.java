/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chargementControllers;

import cls.evenements;
import static cls.evenements.ev;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import interfacesControllers.approvisionnementController;
import static interfacesControllers.approvisionnementController.aprov;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class LoadDetailleApprovController implements Initializable {

    @FXML
    private Label txtnumero;
    @FXML
    private Label txtDesignation;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtPA;
    @FXML
    private FontAwesomeIconView btn_save;
    @FXML
    private FontAwesomeIconView btn_delete;
    public static String Article;
    public static String Numero;
    public static String Qte;
    public static String Pa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDesignation.setText(Article);
        txtnumero.setText(Numero);
        txtPA.setText(Pa);
        txtQuantite.setText(Qte);
        if (var.getLoad()) {
            btn_delete.setDisable(true);
            btn_save.setDisable(true);
            btn_delete.setOpacity(0.13);
            btn_save.setOpacity(0.13);
        } else {
            if (Integer.parseInt(txtQuantite.getText()) > 0) {
                btn_save.setOpacity(0.13);
                btn_save.setDisable(true);
            }
        }
    }

    @FXML
    private void save(MouseEvent event) throws IOException {
        if (!evenements.isFieldsempty(txtPA, txtQuantite)) {
            if (ev.isNumber(txtQuantite.getText())) {
                if (ev.isNumber(txtPA.getText())) {
                    if (Integer.parseInt(txtQuantite.getText()) > 0) {
                        aprov.Scrool(approvisionnementController.newVBOX, txtDesignation.getText(), Integer.valueOf(txtQuantite.getText()), Float.valueOf(txtPA.getText()));
                        ev.alerteInfos("Produit Ajouté !", true);
                    } else {
                        ev.alerteInfos("La Quantité ou le prix doit etre supperieur a 0 Svp !", false);
                    }
                } else {
                    ev.alerteInfos("La prix d'achat entré est incorrect !", false);
                }
            } else {
                ev.alerteInfos("La Quantité entré est incorrect !", false);
            }
        } else {
            ev.alerteInfos("Veiller resigner tout les champs sur cette ligne !", false);
        }
    }

    @FXML
    private void delete(MouseEvent event) throws IOException {
        aprov.remove(txtDesignation.getText());
        aprov.Scrool(approvisionnementController.newVBOX, null, 0, (float) 0);
        ev.alerteInfos("Produit supprimé !", true);
    }

}
