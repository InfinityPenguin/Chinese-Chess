package generator;
import static generator.Color.RED;

import java.awt.Point;
import javax.swing.ImageIcon;

public class Horse extends Piece {
	public Horse(Point point, Color color, Game game) {
		super(point, color, game);
		name = "Horse";
		icon = new ImageIcon("src/BHorse.gif");
		if (color == RED) 
			icon = new ImageIcon("src/RHorse.gif");
	}

	protected boolean canReachTarget(Point point) {
		int rowDif = Math.abs(getRow() - point.y);
		int colDif = Math.abs(getCol() - point.x);
		return rowDif == 1 && colDif == 2 || 
				rowDif == 2 && colDif == 1;
	}

	protected Point[] pathConstructor(Point point) {
		int y = point.y - getRow();
		int x = point.x - getCol();
		Point[] path = new Point[1];
		if (Math.abs(y) == 2) {
			if (y > 0) 
				path[0] = new Point(getCol(), getRow() + 1);
			else 
				path[0] = new Point(getCol(), getRow() - 1);
			return path;
		}
		if (x > 0) 
			path[0] = new Point(getCol() + 1, getRow());
		else 
			path[0] = new Point(getCol() - 1, getRow());
		return path;
	}
} //end Horse class
