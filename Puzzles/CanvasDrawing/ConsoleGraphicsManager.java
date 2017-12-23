import java.util.*;

/**
 * This class provides an abstraction to retrieve {@link IShape} that can be drawn on the console.
 * It also acts as a facade to perform basic operations like fill on those instances.
 * @author th.nitendra@gmail.com
 */
public class ConsoleGraphicsManager implements GraphicsManager {
    private ConsoleDrawingLogic drawLogic;
    private Canvas canvas = new Canvas(1, 1);

    public void resizeCanvas(int w, int h) {
        canvas.resize(w, h);
        drawLogic = new ConsoleDrawingLogic(canvas);
    }

    public IShape getLine(Point p1, Point p2) {
        return new Line(p1, p2);
    }

    public IShape getRectangle(Point p1, Point p2) {
        if(p1.X() == p2.X() || p1.Y() == p2.Y()) {
            throw new RuntimeException("Cannot construct a rectangle with given points " + p1 + p2);
        }
        Point topLeft = p1.X() < p2.X() ? p1 : p2;
        Point bottomRight = p1.Y() < p2.Y() ? p2 : p1;
        Point bottomLeft = Point.getInstance(topLeft.X(), bottomRight.Y());
        Point topRight = Point.getInstance(bottomRight.X(), topLeft.Y());

        IShape rectangle = new IShape() {
            @Override
            public List<IShape> getSubShapes() {
                return Arrays.asList(getLine(topLeft, topRight),
                        getLine(bottomLeft, bottomRight),
                        getLine(topLeft, bottomLeft),
                        getLine(topRight, bottomRight));
            }

            @Override
            public List<Point> getCoordinates() {
                return Arrays.asList(topLeft, bottomRight);
            }

            @Override
            public List<Point> getPoints() {
                final List<Point> points = new ArrayList<>();
                getSubShapes().forEach(s -> {
                    points.addAll(s.getPoints());
                });
                return points;
            }
        };

        return rectangle;
    }

    @Override
    public void draw() {
        drawLogic.draw();
    }

    @Override
    public GraphicsManager addToCanvas(IShape shape) {
        canvas.add(shape);
        return this;
    }

    public void fillConnectedAreas(Point point, String color ) {
        drawLogic.fillConnectedAreas(point, color);
    }

    public IDrawingLogic getDrawingLogic() {
        return drawLogic;
    }

    public void setDrawLogic(ConsoleDrawingLogic drawLogic) {
        this.drawLogic = drawLogic;
    }

}
