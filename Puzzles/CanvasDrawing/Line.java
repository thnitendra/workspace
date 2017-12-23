import java.util.*;

/**
 * Line is a fundamental geomatrical shape that is used to construct various linear shapes.
 * @author th.nitendra@gmail.com
 */
public class Line implements IShape {
    private Point p1, p2;

    public Line(Point p1, Point p2) {
        if(p1.equals(p2)) {
            throw new RuntimeException("Could not construct a line because both points are same.");
        }
        if(p1.Y() == p2.Y()) {
            if (p1.X() < p2.X()) {
                this.p1 = p1;
                this.p2 = p2;
            } else if (p1.X() > p2.X()) {
                this.p1 = p1;
                this.p2 = p2;
            }
        } else if(p1.X() == p2.X()) {
            if (p1.Y() < p2.Y()) {
                this.p1 = p1;
                this.p2 = p2;
            } else if (p1.Y() > p2.Y()) {
                this.p1 = p1;
                this.p2 = p2;
            }
        } else {
            throw new RuntimeException("Currently, only vertical and horizontal lines are supported. But input points can not construct such a line " + p1 + p2);
        }
    }

    public boolean isVertical() {
        return p1.X() == p2.X();
    }

    public boolean isHorizontal() {
        return p1.Y() == p2.Y();
    }

    @Override
    public List<IShape> getSubShapes() {
        return Collections.emptyList();
    }

    @Override
    public List<Point> getCoordinates() {
        return Arrays.asList(p1, p2);
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        Point p = p1;
        if(isHorizontal()) {
            do {
                points.add(p);
                p = p.right();
            } while(!p.equals(p2));
        } else if(isVertical()) {
            do {
                points.add(p);
                p = p.down();
            } while(!p.equals(p2));
        }
        points.add(p2);
        return points;
    }
}
