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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class AddFournisseurController implements Initializable {

    @FXML
    private Text title;
    @FXML
    private JFXButton btn_delete;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtEmail;
    @FXML
    private JFXButton btAdd1;
    @FXML
    private FontAwesomeIconView font;
    @FXML
    private Label labInfos;
    @FXML
    private TextField txtAdresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_delete.setDisable(true);
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) throws SQLException {
        if (evenements.isFieldsempty(txtAdresse, txtEmail, txtNom, txtNumero)) {
            ev.showMssge(labInfos, font, "Aucune case ne doit etre vite !", 0);
        } else {
            if (ev.isValideTel(txtNumero.getText())) {
                if (ev.isValidMail(txtEmail.getText())) {
                    ResultSet rs = Querry.getResult("call addFournisseur('" + txtNom.getText() + "','" + txtNumero.getText() + "','" + txtEmail.getText() + "','" + txtAdresse.getText() + "')");
                    if (rs.next()) {
                        if (rs.getString(1).equals("exist")) {
                            ev.showMssge(labInfos, font, "Ce fournisseur existe deja !", 0);
                        } else if (rs.getString(1).equals("success")) {
                            ev.showMssge(labInfos, font, "fournisseur Ajouté avec succès !", 1);
                        }
                    }
                } else {
                    ev.showMssge(labInfos, font, "Votre Adresse mail n'est pas correcte !", 0);
                }
            } else {
                ev.showMssge(labInfos, font, "Votre Numérode de télephone n'est pas correcte !", 0);
            }
        }
    }

}
