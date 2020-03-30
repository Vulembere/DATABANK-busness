package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static cls.references.*;
import dbo.connexion;
import javafx.stage.StageStyle;


/**
 *
 * @author SMART PC
 */
public class Main extends Application {

    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PRINCIPAL));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("DATABANK BUSNESS 1.0");

            stage.setResizable(Boolean.TRUE);
            stage.initStyle(StageStyle.DECORATED);
            stage.getIcons().add(new javafx.scene.image.Image(ICON));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connexion.Con();
        launch(args);
    }
}
