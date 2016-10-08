package nit.rakuten.codility;

/**
 (0,0)
 x1,y1			x2, y1 
    __________
 * |		  |
 * |		  |
 * |__________|
 x1, y2			x2, y2
 
 There are assumptions with this solution:
 1. rectangles are always aligned with the coordinate axis 
 2. (0,0) coordinate is top left corner 
 3. The intersecting area forms a new rectangle. This is automatically achieved as an outcome of first  
 
 * @author Thakur Nitendra
 *
 */

public class Rectangle_Solution {
	public static Rectangle intersect(Rectangle r1, Rectangle r2) {
		Coordinates[] r1c = r1.coordinates();
		Coordinates[] r2c = r2.coordinates();
		
		// Get intersecting rectangle coordinates
		Coordinates ic1 = new Coordinates(
				Double.max(r1c[0].X(), r2c[0].X()), 
				Double.max(r1c[0].Y(), r2c[0].Y()));

		Coordinates ic2 = new Coordinates(
				Double.min(r1c[1].X(), r2c[1].X()), 
				Double.min(r1c[1].Y(), r2c[1].Y()));

		return new Rectangle(ic1, ic2);
	}
	
	// Test
	public static void main(String[] args) {
		Coordinates r1c1 = new Coordinates(5, 5);
		Coordinates r1c2 = new Coordinates(20, 13);
		Coordinates r2c1 = new Coordinates(10, 10);
		Coordinates r2c2 = new Coordinates(25, 18);
		
		Rectangle r1 = new Rectangle(r1c1, r1c2);
		Rectangle r2 = new Rectangle(r2c1, r2c2);
		Rectangle r3 = intersect(r1, r1);
		
		System.out.println("Rectangle1:" + r1);
		System.out.println("Rectangle2:" + r2);
		System.out.println("Intersecting Rectangle:" + r3);
				
	}
}

class Coordinates {
	double x, y;
	public Coordinates(double x, double y) {
		this.x = x;
		this.y = y;
	}	
	public double X() {return x;}
	public double Y() {return y;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}
}

class Rectangle {
	private Coordinates c1, c2;
	private double ln, wd;
	
	public Rectangle(double length, double width) {
		this.c1 = new Coordinates(0, 0);
		this.c2 = new Coordinates(length, width);
		this.ln = length;
		this.wd = width;
	}
	
	public Rectangle(Coordinates c1, Coordinates c2) {
		
		assert c1.X() < c2.X() && c1.Y() < c2.Y();
		
		this.c1 = c1;
		this.c2 = c2;
		this.ln = c2.X() - c1.X();
		this.wd = c2.Y() - c1.Y();		
	}
	
	public double area() {
		return  ln * wd;
	}
	public double length() { return ln; }
	public double width() { return wd; }
	public Coordinates[] coordinates() { return new Coordinates[] {c1, c2};}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
		result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (c1 == null) {
			if (other.c1 != null)
				return false;
		} else if (!c1.equals(other.c1))
			return false;
		if (c2 == null) {
			if (other.c2 != null)
				return false;
		} else if (!c2.equals(other.c2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rectangle [c1=" + c1 + ", c2=" + c2 + "]";
	}
}
