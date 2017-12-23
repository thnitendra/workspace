/**
 * An implementation of this interface will provide common operations about {@link IShape}
 * @author th.nitendra@gmail.com
 */
public interface GraphicsManager {
    IShape getLine(Point p1, Point p2);

    IShape getRectangle(Point p1, Point p2);

    void draw();

    GraphicsManager addToCanvas(IShape shape);
}
