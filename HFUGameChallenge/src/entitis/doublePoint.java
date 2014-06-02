package entitis;

import java.awt.Point;

public class doublePoint {
	public Point startPoint;
	public Point endPoint;
	
	
	public doublePoint(int sX, int sY, int eX, int eY) {
		super();
		
		this.startPoint = new Point(sX,sY);
		this.endPoint = new Point(eX, eY);
	}
	
}
