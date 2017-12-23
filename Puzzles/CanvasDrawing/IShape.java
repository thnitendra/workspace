import java.util.List;

/**
 * Represents any geometrical shape.
 * The two most common possible implementations could be for a Linear shape and Angular shapes.
 * As of now, only Linear (Line based) shapes are supported.
 * @author th.nitendra@gmail.com
 */
public interface IShape {
    List<IShape> getSubShapes();
    List<Point> getCoordinates();
    List<Point> getPoints();
}
