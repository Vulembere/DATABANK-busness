/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Authentique
 */
public class Chart {

    public static void Areachat(AreaChart areaChart, ArrayList DATE, ArrayList VALUES) {
        try {
            XYChart.Series series = new XYChart.Series();
            int x = 0;
            while (x < DATE.size()) {
                series.getData().add(new XYChart.Data(DATE.get(x), VALUES.get(x)));
                x++;
            }
            areaChart.getData().addAll(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Mes variables
     */
    /**
     *
     * @param stck
     * @param entre
     * @param Sortie
     * @param width
     * @param heigth
     * @Pie Chart
     */
    public static void APiechat(StackPane stck, Float entre, Float Sortie, double width, double heigth) {
        PieChart pieChart;
        PieChart.Data slice1;
        PieChart.Data slice2;
        try {
            pieChart = new PieChart();

            slice1 = new PieChart.Data("Entrée", entre);
            slice2 = new PieChart.Data("Sortie", Sortie);

            stck.setPrefSize(width, heigth);
            pieChart.getData().add(slice1);
            pieChart.getData().add(slice2);
            stck.getChildren().add(pieChart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Annimation sur PieChart
     * @ Nb: Elle est en Test
     * @D'ailleurs elle ne fonctionne pas
     */
    static Timeline timeline = new Timeline();

//    public static void ani(double entre, double Sortie) {
//        PieChart.Data slice1;
//        PieChart pieChart;
//        PieChart.Data slice2;
//        timeline.getKeyFrames().add(
//                new KeyFrame(Duration.millis(200), (ActionEvent actionEvent) -> {
//                    slice1 = new PieChart.Data("Entrée", entre);
//                    slice2 = new PieChart.Data("Sortie", Sortie);
//                }
//                ));
//
//        timeline.setCycleCount(1000);
//        timeline.setAutoReverse(true);  //!?
//        timeline.play();
//    }

}
