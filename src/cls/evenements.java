package cls;

import GUIControllers.PrincipalController;
import animatefx.animation.ZoomInLeft;
import animatefx.animation.ZoomInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wills
 */
public class evenements {

    private static JFXDialog dialog;
    private static PopOver over = new PopOver();

    public void hideForm(Node node) {
        ((Stage) node.getScene().getWindow()).hide();
    }

    public static void PopOverMenu(Node node, URL url, PopOver.ArrowLocation arrowLocation) throws IOException {
        if (!over.isShowing()) {
            AnchorPane box = FXMLLoader.load(url);
            over.setArrowLocation(arrowLocation);
            over.setAutoHide(true);
            over.setContentNode(box);
            over.show(node, 0);
        } else {
            over.hide();
        }
    }

    public static void PopOverMenuVB(Node node, URL url, PopOver.ArrowLocation arrowLocation) throws IOException {
        if (!over.isShowing()) {
            VBox box = FXMLLoader.load(url);
            over.setArrowLocation(arrowLocation);
            over.setAutoHide(true);
            over.setContentNode(box);
            over.show(node, 0);
        } else {
            over.hide();
        }
    }

    public void showFormDialog(String Chemainc) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource(Chemainc));
        JFXDialogLayout dl = new JFXDialogLayout();
        dl.setPadding(Insets.EMPTY);
        dl.setBody(node);
        dialog = new JFXDialog(PrincipalController.principalStock, dl, JFXDialog.DialogTransition.CENTER, false);
        dialog.show(PrincipalController.principalStock);
    }

    public void SelectDataFor(Button... bt) {
        try {
            for (Button tr : bt) {
                tr.setStyle("-fx-background-color: #fff;-fx-text-fill: #000000;");
                tr.setFont(Font.font("System", FontPosture.REGULAR, 13));
            }
            bt[0].setStyle("-fx-background-color: #6A75CA;-fx-text-fill: #fff;");
            bt[0].setFont(Font.font("System", FontWeight.BOLD, 13));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void Dialog(String file) throws IOException {
//        Node node = FXMLLoader.load(getClass().getResource("/Dialog/" + file + ".fxml"));
//        JFXDialogLayout dl = new JFXDialogLayout();
//        dl.setPadding(Insets.EMPTY);
//        dl.setBody(node);
//        dialog = new JFXDialog(MainController.newMainStack, dl, JFXDialog.DialogTransition.CENTER, false);
//        dialog.show(MainController.newMainStack);
//    }
    public void setForm(Node node, String url) throws IOException {
        try {
            Node child = FXMLLoader.load(getClass().getResource(url));
            if (node instanceof StackPane) {
                StackPane contain_area = (StackPane) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (node instanceof VBox) {
                VBox contain_area = (VBox) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            } else if (node instanceof AnchorPane) {
                AnchorPane contain_area = (AnchorPane) node;
                contain_area.getChildren().removeAll();
                contain_area.getChildren().setAll(child);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void OpenWindows(String URL, String title, Boolean resizable) {
        Stage parentStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(URL));
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
                stage.initModality(Modality.APPLICATION_MODAL);
            } else {
                stage = new Stage(StageStyle.UNDECORATED);
            }
            stage.setResizable(resizable);
            stage.setTitle(title);
            stage.setMaximized(true);
            stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("UP-DEPP 1.0");
            stage.getIcons().add(new javafx.scene.image.Image("/icons/crocIcon.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public void setAllMouv(Node element) {
//        new ZoomInRight(element);
//    }
//
//    public void setMouv(Label lb, FontAwesomeIconView icon) {
//        lb.setVisible(true);
//        icon.setVisible(true);
//        new ZoomInRight(lb).play();
//        new ZoomInRight(icon).play();
//    }
    public static boolean isFieldsempty(Node... field) {
        int i = 0;
        while (i < field.length) {
            if (field[i] instanceof TextField) {
                TextField text = (TextField) field[i];
                if (text.getText() == null || text.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof PasswordField) {
                PasswordField pass = (PasswordField) field[i];
                if (pass.getText() == null || pass.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) field[i];
                if (comboBox.getValue() == null) {
                    break;
                }
                i++;
            } else if (field[i] instanceof DatePicker) {
                DatePicker date = (DatePicker) field[i];
                if (date.getValue() == null) {
                    break;
                }
                i++;
            } else if (field[i] instanceof TextArea) {
                TextArea area = (TextArea) field[i];
                if (area.getText().trim().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof Label) {
                Label txt = (Label) field[i];
                if (txt.getText() == null || txt.getText().isEmpty()) {
                    break;
                }
                i++;
            } else if (field[i] instanceof Text) {
                Text txt = (Text) field[i];
                if (txt.getText() == null) {
                    break;
                }
                i++;
            }
        }
        return i != field.length;
    }

    public static void initFields(Node... field) {
        for (Node f : field) {
            if (f instanceof TextField) {
                TextField text = (TextField) f;
                text.setText(null);
            } else if (f instanceof DatePicker) {
                DatePicker text = (DatePicker) f;
                text.setValue(null);
            } else if (f instanceof TextArea) {
                TextArea text = (TextArea) f;
                text.setText(null);
            } else if (f instanceof ComboBox) {
                ComboBox text = (ComboBox) f;
                text.setValue(null);
            } else if (f instanceof Label) {
                Label txt = (Label) f;
                txt.setText(null);
            } else if (f instanceof Text) {
                Text txt = (Text) f;
                txt.setText(null);
            } else if (f instanceof PasswordField) {
                PasswordField txt = (PasswordField) f;
                txt.setText(null);
            }
        }
    }

    public void alerteInfos(String message, boolean bool) {

        Notifications notificationsErreur = Notifications.create()
                .title("Message d'Alert")
                .text("\n                                 " + message)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_RIGHT)
                .onAction((ActionEvent event) -> {
                });
        if (bool) {
            notificationsErreur.showInformation();
        } else {
            notificationsErreur.showError();
        }

    }

    public static void IsSeleted(Label... lab) {
        try {
            for (Label tr : lab) {
                tr.setTextFill(Color.web("b9b9b9"));
                tr.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 13));
            }
            lab[0].setTextFill(Color.web("#393636"));
            lab[0].setFont(Font.font("Century Gothic", FontWeight.BOLD, 13));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SetImage(ImageView imgV, String patch) throws FileNotFoundException {
        imgV.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream(patch), 100, 200, false, true));
    }

    public void showMssge(Label lab, FontAwesomeIconView icon, String mssg, int etat) {
        if (etat == 1) {
            lab.setVisible(true);
            icon.setVisible(true);
            icon.setIcon(FontAwesomeIcon.CHECK);
            icon.setFill(Paint.valueOf("#a7c9b0"));
            lab.setTextFill(Color.web("#a7c9b0"));
            lab.setText(mssg);
            new ZoomInRight(lab).play();
            new ZoomInRight(icon).play();
        } else {
            lab.setVisible(true);
            icon.setVisible(true);
            icon.setIcon(FontAwesomeIcon.EXCLAMATION_TRIANGLE);
            icon.setFill(Paint.valueOf("#ff0000"));
            lab.setTextFill(Color.RED);
            lab.setText(mssg);
            new ZoomInRight(lab).play();
            new ZoomInRight(icon).play();
        }
        try {
            lab.setVisible(true);

            Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        new ZoomInLeft(lab).play();
                        new ZoomInLeft(icon).play();
                        lab.setVisible(false);
                        icon.setVisible(false);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(evenements.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            th.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public boolean isNumber(String txtString) {
        char[] tab = txtString.toCharArray();
        boolean estUnNombre = true;
        for (int i = 0; i < tab.length; i++) {
            if (Character.isDigit(tab[i])) {
            } // ne rien faire ;-)
            else {
                if (tab[i] != '.' && tab[i] != '-' && tab[i] != '*') {
                    estUnNombre = false;
                }
            } // remplace le point '.' par virgule ',' ou ajoute un caractère comme '+' ;-)
        }
        return estUnNombre;
    }

    private static Pattern p;
    private static Matcher m;

    public boolean isValideTel(String txtString) {
        p = Pattern.compile("^[+0-9-\\(\\)\\s]*{6,14}$");
        m = p.matcher(txtString);
        return m.matches();
    }

    public void showExitDialog(StackPane rootPane, JFXDialog.DialogTransition transition) {
        try {

            JFXDialogLayout dl = new JFXDialogLayout();
            FontAwesomeIconView icon = new FontAwesomeIconView();
            icon.setIcon(FontAwesomeIcon.EXCLAMATION_TRIANGLE);
            icon.setFill(Paint.valueOf("#a7c9b0"));
            dl.setBody(new Label("Voulez-vous quitter l'application ?"));
            dialog = new JFXDialog(rootPane, dl, transition, false);

            JFXButton ok = new JFXButton("Oui");
            ok.setOnAction(e -> {
//                ev.hideForm(MainController.contenaire);
                ev.OpenWindows("/GUI/login.fxml", "UP-DEPP 1.0", Boolean.TRUE);
            });
            JFXButton no = new JFXButton("Non");
            no.setOnAction(e -> {
                dialog.close();
            });

            dl.setActions(ok, no);
            dialog.show(rootPane);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openBrowser(String url) {
        try {
            Desktop d = Desktop.getDesktop();
            d.browse(new URI(url));
        } catch (URISyntaxException | IOException e) {
            System.out.println("# " + e.getMessage());
        }
    }

    public static void SetToolTip(Node node, String str) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(str);
        if (node instanceof Label) {
            Label txt = (Label) node;
            txt.setTooltip(tooltip);
            System.err.println("#Label");
        } else if (node instanceof JFXButton) {
            JFXButton txt = (JFXButton) node;
            txt.setTooltip(tooltip);
            System.err.println("#Buton ");
        } else {
            System.err.println("#Null Object");
        }
    }

    int i;
    public int l_index;
    int tot, size;
    Label lb = new Label();

    static String genre;
    static MaterialDesignIconView designIcon;

    /**
     * Fonctions & Methodes
     *
     * @param str2
     * @param lab
     * @return
     * @throws java.lang.Exception
     */
    //#1
    public static String getDifGenre(String str2, Label lab) throws Exception {

        if ("Féminin".equals(str2)) {

            designIcon = new MaterialDesignIconView(MaterialDesignIcon.HUMAN_MALE);
            designIcon.setGlyphSize(20);
            designIcon.setStyle("-fx-fill:#48477B");
            genre = "Masculin";
        } else {
            designIcon = new MaterialDesignIconView(MaterialDesignIcon.HUMAN_FEMALE);
            designIcon.setGlyphSize(20);
            designIcon.setStyle("-fx-fill:#48477B");
            genre = "Féminin";
        }

        lab.setGraphic(designIcon);

        return genre;
    }
  public static String Premier_Maj(String str) {
        String _fst = ("" + str.charAt(0)).toUpperCase();
        String scnd = ("" + str.substring(1, str.length())).toLowerCase();
        str = _fst + scnd;
        return str;
    }
    //#2
//    public void ShowDialog(String url, double width, double height) throws IOException {
//        Node node = FXMLLoader.load(getClass().getResource(url));
//        JFXDialogLayout dl = new JFXDialogLayout();
//        dl.setPadding(Insets.EMPTY);
//        dl.setBody(node);
//        dl.setPrefSize(width, height);
//        dialog = new JFXDialog(PrincipalController.rstck, dl, JFXDialog.DialogTransition.CENTER, false);
//        dialog.show(PrincipalController.rstck);
//    }
    public static evenements ev = new evenements();
}
