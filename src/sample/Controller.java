package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Controller {

    int[] x = {10,   20,  40,  60,  80,  90, 50, 45, 55, 50};
    int[] y = {250, 250, 200, 200, 250, 250, 150, 190, 190, 170};

    int[] p1 = {0,1,2,3,4,5,6,0};
    int[] p2 = {7,8,9,7};

    @FXML ColorPicker colorPicker;

    @FXML Canvas canvas;
    PixelWriter pw;

    public void drawFigure(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Color color = colorPicker.getValue();
        pw = gc.getPixelWriter();
//        line(10,300, 650, 20, Color.BLACK);
        for (int i=0; i<p1.length-1; i++) {
            line(x[p1[i]],y[p1[i]], x[p1[i+1]],y[p1[i+1]], color);
        }
        for (int i=0; i<p2.length-1; i++) {
            line(x[p2[i]],y[p2[i]], x[p2[i+1]],y[p2[i+1]], color);
        }
    }

    public void line(int x1, int y1, int x2, int y2, Color color) {
        int xErr = 0, yErr = 0;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int incX = x2 > x1 ? 1 : x2 < x1 ? -1 : 0;
        int incY = y2 > y1 ? 1 : y2 < y1 ? -1 : 0;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        int d = dx > dy ? dx : dy;
        int x = x1, y = y1;
        pw.setColor(x,y,color);
        for (int i = 0; i < d; i++) {
            xErr += dx; yErr += dy;
            if (xErr > d) {
                xErr -= d;
                x += incX;
            }
            if (yErr > d) {
                yErr -= d;
                y += incY;
            }
            pw.setColor(x,y,color);
        }
    }
}
