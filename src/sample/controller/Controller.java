package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.function.Function1;
import sample.function.Function2;

import static sample.function.HalfMiddle.bisection;
import static sample.utils.Utils.writeToFile;

public class Controller {

    private static final int N = 1000;

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

    public void buttonAction1(ActionEvent actionEvent) {
        int count = 0;
        double x = -3;
        for (int i = 0; i < 50; i++) {
            double y1 = -3;
            double y2 = -3 + 0.1;
            int rootsCount = 0;

            for (int j = 0; j < 50; j++) {
                if (fx1.f(x, y1) * fx1.f(x, y2) < 0) {
                    roots[rootsCount] = Math.round(bisection(fx1, y1, y2, x) * 1000) / 1000.0;
                    Y[count] = roots[rootsCount];
                    X[count] = Math.round(x * 1000) / 1000.0;
                    count++;
                    rootsCount++;
                }
                y1 += 0.1;
                y2 += 0.1;
            }
            x += 0.1;
        }

        for (int i = 0; i < count; i++) {
            textArea1.appendText(X[i] + " ");
            textArea1.appendText(Y[i] + "\r\n");
        }

        String text = textArea1.getText();
        writeToFile(text, "note.txt");
    }

    public void buttonAction2(ActionEvent actionEvent) {
        int count = 0;
        double z = -4;
        for (int i = 0; i < 50; i++) {
            double x = -3;
            for (int j = 0; j < 50; j++) {
                double y1 = -3;
                double y2 = -3 + 0.1;
                int rootsCount = 0;

                for (int k = 0; k < 100; k++) {
                    if (fx2.f(x, y1, z) * fx2.f(x, y2, z) < 0) {
                        roots[rootsCount] = Math.round(bisection(fx2, y1, y2, x, z) * 1000) / 1000.0;
                        X[count] = Math.round(x * 1000) / 1000.0;
                        Y[count] = roots[rootsCount];
                        Z[count] = Math.round(z * 1000) / 1000.0;
                        count++;
                        rootsCount++;
                    }
                    y1 += 0.1;
                    y2 += 0.1;
                }
                x += 0.2;
            }
            z += 0.5;
        }

        for (int i = 0; i < count; i++) {
            textArea2.appendText(X[i] + " ");
            textArea2.appendText(Y[i] + " ");
            textArea2.appendText(Z[i] + "\r\n");
        }

        String text = textArea2.getText();
        writeToFile(text, "note2.txt");
    }
}
