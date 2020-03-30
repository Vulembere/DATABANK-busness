/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesControllers;

import chargementControllers.LoadArticleController;
import static cls.evenements.ev;
import static cls.references.AJOUT_ARTICLE;
import dbo.Querry;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import traitement.var;

/**
 * FXML Controller class
 *
 * @author wills
 */
public class ArticleController implements Initializable {

    @FXML
    private TextField txtRechercher;
    @FXML
    private Text txtNombre;
    @FXML
    private VBox vbx;
    ArrayList<String> Designation = new ArrayList();
    ArrayList<String> Infos = new ArrayList();
    ArrayList<Integer> Qte = new ArrayList();
    ArrayList<Integer> QteMin = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            load(Querry.getResult("select * from v_article order by article"));
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void load(ResultSet rs) throws SQLException, IOException {
        init();
        while (rs.next()) {
            Designation.add(rs.getString(2));
            Qte.add(rs.getInt(5));
            QteMin.add(rs.getInt(6));
            Infos.add("Catégorie : " + rs.getString(4) + " , Quantité : " + rs.getString(5) + " " + rs.getString(7) + " , Prix Gros : " + rs.getString(8) + " , Prix Detaille : " + rs.getString(9)+", Qte Minimum : "+rs.getInt(6));
        }
        int x = 0;
        txtNombre.setText("0" + String.valueOf(Designation.size()));
        AnchorPane[] node = new AnchorPane[Designation.size()];
        vbx.getChildren().clear();
        while (x < Designation.size()) {
            LoadArticleController.Nom = Designation.get(x);
            LoadArticleController.Infos = Infos.get(x);
            LoadArticleController.Qte = Qte.get(x);
            LoadArticleController.QteMin = QteMin.get(x);
             System.err.println(Qte.get(x)+" ### "+QteMin.get(x));
            node[x] = FXMLLoader.load(getClass().getResource("/chargement/loadArticle.fxml"));
            vbx.getChildren().add(node[x]);
            x++;
        }
    }

    void init() {
        Designation.clear();
        Infos.clear();
        Qte.clear();
        QteMin.clear();
    }

    @FXML
    private void NouveauArticle(ActionEvent event) throws IOException {
        ev.showFormDialog(AJOUT_ARTICLE);
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException, IOException {
        load(Querry.getResult("select * from v_article order by article"));
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException, IOException {
        load(Querry.getResult("select * from v_article where type like '%" + txtRechercher.getText().toUpperCase() + "%' or article like '%" + txtRechercher.getText().toUpperCase() + "%' or Categorie like '%" + txtRechercher.getText().toUpperCase() + "%'"));
    }

}
