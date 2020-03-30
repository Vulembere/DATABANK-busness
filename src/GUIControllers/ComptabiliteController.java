/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class ComptabiliteController implements Initializable {

    @FXML
    private VBox vbx;
    @FXML
    private Label indicator;
    @FXML
    private Label b_Operation;
    @FXML
    private Label b_rapport;
    @FXML
    private Label b_Journalisation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void callOperation(MouseEvent event) {
    }

    @FXML
    private void CallRapport(MouseEvent event) {
    }

    @FXML
    private void CallJournalisation(MouseEvent event) {
    }

    
}
