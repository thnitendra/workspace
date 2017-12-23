/**
 * Provides a logic for drawing a {@link IShape}
 * @author th.nitendra@gmail.com
 */
public interface IDrawingLogic {
    void draw();
    void fillConnectedAreas(Point point, String color);
}
