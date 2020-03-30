/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIControllers;

import cls.evenements;
import static cls.references.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class PrincipalController implements Initializable {

    @FXML
    private StackPane stck;
    @FXML
    private AnchorPane pan1;
    @FXML
    private Label b_dash;
    @FXML
    private AnchorPane pan2;
    @FXML
    private Label b_stock;
    @FXML
    private AnchorPane pan3;
    @FXML
    private Label b_rapport;
    @FXML
    private AnchorPane pan4;
    @FXML
    private Label b_parametre;
    @FXML
    private AnchorPane pan21;
    @FXML
    private Label b_Caisse;
    @FXML
    private AnchorPane pan211;
    @FXML
    private Label b_Comptabilite;
    @FXML
    private AnchorPane pan32;
    @FXML
    private Label b_personnelle;
    @FXML
    private Text txUserName;
    @FXML
    private Text txtFonctionuser;
    @FXML
    private StackPane content;
    public static StackPane principalStock;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        principalStock=stck;
    }

    @FXML
    private void Calldash(MouseEvent event) {
    }

    @FXML
    private void CallStock(MouseEvent event) throws IOException {
        evenements.ev.setForm(content, STOCK);
    }

    @FXML
    private void Callrapport(MouseEvent event) {
    }

    @FXML
    private void CallParametre(MouseEvent event) {
    }

    @FXML
    private void CallCaisse(MouseEvent event) throws IOException {
         evenements.ev.setForm(content, CAISSE);
    }

    @FXML
    private void CallComptabilite(MouseEvent event) throws IOException {
        evenements.ev.setForm(content, COMPTABILITE);
    }

    @FXML
    private void CallPersonnelle(MouseEvent event) throws IOException {
         evenements.ev.setForm(content, PERSONNELLE);
    }

}
