/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chargementControllers;

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
import javafx.scene.input.MouseEvent;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class loadArticleApprovController implements Initializable {
    
    @FXML
    private Label txtNom;
    @FXML
    private Label txtInfos;
    @FXML
    private FontAwesomeIconView btn_afficher;
    public static String Nom;
    public static String Infos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtInfos.setText(Infos);
        txtNom.setText(Nom);
    }
    
    @FXML
    private void Afficher(MouseEvent event) throws IOException {        
        var.setLoad(false);
        if (!aprov.ARTICLE.contains(txtNom.getText())) {
            aprov.Scrool(approvisionnementController.newVBOX, txtNom.getText(),0,(float)0);
        } else {
            ev.alerteInfos("Vous avez deja ajouter cet Article !", true);
        }
        
    }
    
}
