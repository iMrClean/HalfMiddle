package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.function.Function1;
import sample.function.Function2;

import static sample.function.HalfMiddle.bisection;
import static sample.utils.Utils.writeToFile;

public class Controller {

    private static final int N = 10000;

    @FXML
    private TextField a;

    @FXML
    private TextField b;

    @FXML
    private TextField step;

    @FXML
    private TextArea textArea1;

    @FXML
    private TextArea textArea2;

    private Function1 fx1 = (x, y) -> Math.pow(x, 3) + Math.pow(y, 3) - 3 * x * y;

    private Function2 fx2 = (x, y, z) -> 2 * Math.pow(y, 2) + Math.pow(z, 2) - (2 * x);

    private double[] X = new double[N];
    private double[] Y = new double[N];
    private double[] Z = new double[N];
    private double[] roots = new double[N];

    public void initialize() {
        a.setText("-3");
        b.setText("3");
        step.setText("0.1");
    }

    public void buttonAction1(ActionEvent actionEvent) {
        textArea1.clear();
        try {
            int count = 0;
            double startX = Double.parseDouble(a.getText());
            double end = Double.parseDouble(b.getText());
            double steps = Double.parseDouble(step.getText());

            while (startX <= end) {
                int rootsCount = 0;
                double startY = Double.parseDouble(a.getText());
                double delta = Double.parseDouble(a.getText()) + Double.parseDouble(step.getText());

                while (startY <= end) {
                    if (fx1.f(startX, startY) * fx1.f(startX, delta) < 0) {
                        roots[rootsCount] = Math.round(bisection(fx1, startY, delta, startX) * 1000) / 1000.0;
                        Y[count] = roots[rootsCount];
                        X[count] = Math.round(startX * 1000) / 1000.0;
                        count++;
                        rootsCount++;
                    }
                    startY += steps;
                    delta += steps;
                }
                startX += steps;
            }

            for (int i = 0; i < count; i++) {
                textArea1.appendText(X[i] + " ");
                textArea1.appendText(Y[i] + "\r\n");
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверно заполнены поля или они пустые");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        String text = textArea1.getText();
        writeToFile(text, "note.txt");
    }

    public void buttonAction2(ActionEvent actionEvent) {
        textArea2.clear();
        try {
            int count = 0;
            double startZ = Double.parseDouble(a.getText());
            double end = Double.parseDouble(b.getText());
            double steps = Double.parseDouble(step.getText());

            while (startZ <= end) {
                double startX = Double.parseDouble(a.getText());
                while (startX <= end) {
                    double startY = Double.parseDouble(a.getText());
                    double delta = Double.parseDouble(a.getText()) + Double.parseDouble(step.getText());
                    int rootsCount = 0;

                    while (startY <= end) {
                        if (fx2.f(startX, startY, startZ) * fx2.f(startX, delta, startZ) < 0) {
                            roots[rootsCount] = Math.round(bisection(fx2, startY, delta, startX, startZ) * 1000) / 1000.0;
                            X[count] = Math.round(startX * 1000) / 1000.0;
                            Y[count] = roots[rootsCount];
                            Z[count] = Math.round(startZ * 1000) / 1000.0;
                            count++;
                            rootsCount++;
                        }
                        startY += steps;
                        delta += steps;
                    }
                    startX += steps;
                }
                startZ += steps;
            }

            for (int i = 0; i < count; i++) {
                textArea2.appendText(X[i] + " ");
                textArea2.appendText(Y[i] + " ");
                textArea2.appendText(Z[i] + "\r\n");
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверно заполнены поля или они пустые");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        String text = textArea2.getText();
        writeToFile(text, "note2.txt");
    }
}
