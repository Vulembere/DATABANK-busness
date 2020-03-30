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
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class CaisseController implements Initializable {

    @FXML
    private Label b_Result;
    @FXML
    private Label b_Recouvrement;
    @FXML
    private Label b_verification;
    @FXML
    private VBox vbx;
    @FXML
    private Label indicator;
    @FXML
    private Label b_Operation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void callRstult(MouseEvent event) {
    }

    @FXML
    private void CallRecouvrement(MouseEvent event) {
    }

    @FXML
    private void CallVerification(MouseEvent event) {
    }

    @FXML
    private void CallOperation(MouseEvent event) {
    }
    
}
