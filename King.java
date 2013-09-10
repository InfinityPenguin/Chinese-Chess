package generator;
import static generator.Color.RED;

import java.awt.Point;
import javax.swing.ImageIcon;


public class King extends Piece {
	public King(Point point, Color color, Game game) {
		super(point, color, game);
		name = "King";
		icon = new ImageIcon("src/BKing.gif");
		if (color == RED) 
			icon = new ImageIcon("src/RKing.gif");
	}

	protected boolean canReachTarget(Point point) {
		int rowDif = Math.abs(point.y - getRow());
		int colDif = Math.abs(point.x - getCol());
		return rowDif == 0 && colDif == 1 || 
				rowDif == 1 && colDif == 0;
	}

	protected Point[] pathConstructor(Point point) {
		Point[] path = new Point[0];
		return path;
	}

	@Override
	protected boolean canAttackKing(Piece king) {
		Point[] path = new Point[Math.abs(king.getRow() - getRow()) - 1];
		int pathCounter = 0;
		int from = getRow(); int to = king.getRow();
		if (getCol() == king.getCol()) {
			if (from > to) {int temp = to; to = from; from = temp;}
			for (int pos = from + 1; pos < to; pos++) {
				path[pathCounter] = new Point(getCol(), pos);
				pathCounter++;
			} 
			return pathClear(path);
		}
		return false;
	}

	@Override
	boolean isKing() {
		return true;
	}

	@Override
	boolean canLeavePalace() {
		return false;
	}
} //end King class
