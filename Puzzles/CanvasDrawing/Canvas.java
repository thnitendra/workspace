import java.util.*;

/**
 * Provides an matrix based implementation of a Canvas.
 * @author th.nitendra@gmail.com
 */
public class Canvas {
    private boolean[][] canvas;
    private List<IShape> shapes = new ArrayList<>();

    public Canvas(int w, int h) {
        this.canvas = new boolean[h][w];
    }

    public void resize(int w, int h) {
        boolean newCanvas[][] = new boolean[h][];
        for(int i = 0; i < h; ++i) {
            if(i < canvas.length) {
                newCanvas[i] = Arrays.copyOf(canvas[i], w);
            } else {
                newCanvas[i] = new boolean[w];
            }
        }
        canvas = newCanvas;
    }

    public int width() {
        return canvas[0].length;
    }

    public int height() {
        return canvas.length;
    }

    private boolean checkCoordinates(int x, int y) {
        return x < width() && y < height() && canvas[y][x];
    }

    /**
     * Checks if the given point is marked for drawing?
     */
    public boolean check(Point p) {
        return checkCoordinates(p.X(), p.Y());
    }

    /**
     * Marks the given point for drawing
     */
    public void mark(Point p) {
        if(exist(p)) {
            canvas[p.Y()][p.X()] = true;
        }
    }

    public void unmark(Point p) {
        if (exist(p)) {
            canvas[p.Y()][p.X()] = false;
        }
    }

    /**
     * Checks if the point exists on this canvas?
     */
    public boolean exist(Point p) {
        return p.X() >= 0 && p.X() < width() && p.Y() >= 0 && p.Y() < height();
    }

    public void add(IShape shape) {
        shapes.add(shape);
        shape.getPoints().forEach(p -> {mark(p);});
    }

    /**
     * returns all the points based on the state of a point
     * @param state is the state of a Point - if it marked for drawing?
     * @return
     */
    public List<Point> getAllPoints(boolean state) {
        List<Point> points = new ArrayList<>();
        for (int y = 0; y <= height(); ++y) {
            for (int x = 0; x <= width(); ++x) {
                if(canvas[y][x] == state) {
                    points.add(Point.getInstance(x, y));
                }
            }
        }
        return points;
    }

    /**
     * returns all directly connected points from a given point.
     * @param point
     * @return
     */
    public Set<Point> getAllConnectedPoints(Point point) {
        Set<Point> result = new HashSet<>();
        Set<Point> excluded = new HashSet<>();
        scanConnectedPoints(point, result, excluded);
        return result;
    }

    private void scanConnectedPoints(Point point, Set<Point> result, Set<Point> excluded) {
        if(result.contains(point) || excluded.contains(point)) {
            return;
        }
        if(!exist(point) || check(point)) {
            excluded.add(point);
            return;
        } else {
            result.add(point);
            scanConnectedPoints(point.left(), result, excluded);
            scanConnectedPoints(point.right(), result, excluded);
            scanConnectedPoints(point.up(), result, excluded);
            scanConnectedPoints(point.down(), result, excluded);
        }
    }
}
