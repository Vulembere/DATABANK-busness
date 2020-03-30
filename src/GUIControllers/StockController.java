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

/**
 * FXML Controller class
 *
 * @author wills
 */
public class StockController implements Initializable {

    @FXML
    private Label b_Article;
    @FXML
    private Label b_Approvionnement;
    @FXML
    private AnchorPane pan4;
    @FXML
    private Label b_Etat_Stock;
    @FXML
    private Label b_Ventillation;
    @FXML
    private Label indicator;
    @FXML
    private StackPane contenaire;
    @FXML
    private Label b_Barcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void callArtic(MouseEvent event) throws IOException {
          evenements.ev.setForm(contenaire,ARTICLE );
    }


    @FXML
    private void CallApprovisionnement(MouseEvent event) throws IOException {
        evenements.ev.setForm(contenaire,APPROVISIONNEMENT );
    }

    @FXML
    private void CallEtatStock(MouseEvent event) {
    }

    @FXML
    private void CallVentillation(MouseEvent event) {
    }

    @FXML
    private void CallBarcode(MouseEvent event) throws IOException {
         evenements.ev.setForm(contenaire,BARCODE );
    }
    
}
