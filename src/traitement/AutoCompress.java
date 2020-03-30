/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;

import dbo.connexion;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author VULEMBERE
 */
public class AutoCompress extends connexion {

    public static double xOffset = 0, yOffset = 0;

     static ObservableList AutoCompression(String Table, String Colone) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            pst = Con().prepareStatement("select " + Colone + " from " + Table);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (!list.contains(rs.getString(Colone))) {
                    list.addAll(rs.getString(Colone));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        System.out.print(list.toString());
        return list;
    }

    //  Methode Auto Complete
    public static void Autocomplete(TextField txt, AnchorPane pan) {
        txt.getStyleClass().addAll("AutoCompressField", "AutoCompressField");
        txt.setPrefWidth(323);
        txt.setPromptText("Nom du fournisseur");
        txt.setText("Aucun");
        pan.getChildren().add(txt);
    }

    // FOnction D'affichage
    public static void ChargememtCompression(TextField textFied, String Table, String Colonne) {
        textFied.setOnMouseClicked((e) -> {

            TextFields.bindAutoCompletion(textFied, AutoCompression(Table, Colonne));
        });
    }

    public static AutoCompress AutoCompressExt = new AutoCompress();

    public static void makeStage(Node O) {

        O.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        O.setOnMouseDragged((MouseEvent event) -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
