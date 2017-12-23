import java.util.*;

/**
 * The atomic constituent object to construct a graphical shape.
 * This implementation of Point uses a basic caching using {@link WeakHashMap}. This is ok for a single threaded applications.
 * More sophisticated implementations should be provided for real time apps.
 * @author th.nitendra@gmail.com
 */
class Point {
	private int x, y;
	private static Map<Integer, Map<Integer, Point>> cache = new WeakHashMap<>();

	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int X() {return x;}
	public int Y() {return y;}

	public static Point getInstance(int x, int y) {
		Point p = null;
		Map<Integer, Point> ymap = cache.get(x);
		if(null == ymap) {
			p = new Point(x, y);
			ymap = new WeakHashMap<>();

			ymap.put(y, new Point(x, y));
			cache.put(x, ymap);
		} else {
			p = ymap.get(y);
			if(null == p) {
				p = new Point(x, y);
				ymap.put(y, p);
			}
		}
		return p;
	}

	public Point left() {
		return Point.getInstance(x - 1, y);
	}
	public Point right() {
		return Point.getInstance(x + 1, y);
	}
	public Point up() {
		return Point.getInstance(x, y - 1);
	}
	public Point down() {
		return Point.getInstance(x, y + 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Point point = (Point) o;

		if (x != point.x) return false;
		return y == point.y;

	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}

