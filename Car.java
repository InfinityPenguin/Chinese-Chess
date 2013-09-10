package generator;
import static generator.Color.RED;

import java.awt.Point;
import javax.swing.ImageIcon;

public class Car extends Piece {
	public Car(Point point, Color color, Game game) {
		super(point, color, game);
		name = "Car";
		icon = new ImageIcon("src/BCar.gif");
		if (color == RED) icon = new ImageIcon("src/RCar.gif");
	}

	protected boolean canReachTarget(Point point) {
		if (getRow() == point.y || 
				getCol() == point.x) 
			return true;
		return false;
	}

	protected Point[] pathConstructor(Point point) {
		Point[] path;
		int pathCounter = 0;
		int pos1, pos2, temp;
		pos1 = getRow();
		pos2 = point.y;
		if (point.y - getRow() == 0) {pos1 = getCol(); pos2 = point.x;}
		path = new Point[Math.abs(pos1 - pos2) - 1];
		if (pos1 > pos2) {temp = pos1; pos1 = pos2; pos2 = temp;}
		for (int pos = pos1 + 1; pos < pos2; pos++) {
			if (point.y - getRow() == 0) 
				path[pathCounter] = new Point(pos, getRow());
			else 
				path[pathCounter] = new Point(getCol(), pos);
			pathCounter++;
		} 
		return path;
	}
} //end Car class
