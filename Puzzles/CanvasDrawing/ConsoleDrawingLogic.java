import java.util.*;

/**
 * Provides logic for drawing {@link IShape} on Console.
 * @author th.nitendra@gmail.com
 */
public class ConsoleDrawingLogic implements IDrawingLogic {
    private Canvas canvas;
    private String charPoint = "x";

    public ConsoleDrawingLogic(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw() {
        for (int y = -1; y <= canvas.height(); ++y) {
            for (int x = -1; x <= canvas.width(); ++x) {
                if(y == -1 || y == canvas.height()) {
                    System.out.print("-");
                } else if(x == -1 || x == canvas.width()) {
                    System.out.print("|");
                } else if (canvas.check(Point.getInstance(x, y))) {
                    System.out.print(charPoint);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void fillConnectedAreas(Point point, String color) {
        if(color.length() > 1) {
            throw new RuntimeException("Invalid Color");
        }
        Set<Point> targetPoints = canvas.getAllConnectedPoints(point);
        for (int y = -1; y <= canvas.height(); ++y) {
            for (int x = -1; x <= canvas.width(); ++x) {
                if(y == -1 || y == canvas.height()) {
                    System.out.print("-");
                } else if(x == -1 || x == canvas.width()) {
                    System.out.print("|");
                } else if (canvas.check(Point.getInstance(x, y))) {
                    System.out.print(charPoint);
                } else if (targetPoints.contains(Point.getInstance(x, y))) {
                    System.out.print(color);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getCharPoint() {
        return charPoint;
    }

    public void setCharPoint(String charPoint) {
        this.charPoint = charPoint;
    }
}
