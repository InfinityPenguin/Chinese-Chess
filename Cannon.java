package generator;
import static generator.Color.*;

import java.awt.Point;
import javax.swing.ImageIcon;

public class Cannon extends Piece {
	private boolean hasTarget;
	public Cannon(Point point, Color color, Game game) {
		super(point, color, game);
		name = "Cannon";
		icon = new ImageIcon("src/BCannon.gif");
		if (color == RED) 
			icon = new ImageIcon("src/RCannon.gif");
	}

	protected boolean canReachTarget(Point point) {
		if (getRow() == point.y || 
				getCol() == point.x) 
			return true;
		return false;
	}

	@Override
	protected boolean pathClear(Point[] path) {
		if (hasTarget) {
			int needToJump = 1;
			for (Point spot : path) 
				if (game.getPiece(spot) != null) 
					needToJump--;
			return needToJump == 0;
		}
		for(Point spot : path) 
			if (game.getPiece(spot) != null) 
				return false;
		return true; 
	}

	protected Point[] pathConstructor(Point point) {
		hasTarget = false;
		if (game.getPiece(point) != null) 
			hasTarget = true;
		Point[] path;
		int pathCounter = 0;
		int pos1, pos2, temp;
		pos1 = getRow();
		pos2 = point.y;
		if (point.y - getRow() == 0) {pos1 = getCol(); pos2 = point.x;}
		path = new Point[Math.abs(pos1 - pos2) - 1];
		if (pos1 > pos2) {temp = pos1; pos1 = pos2; pos2 = temp;}
		for (int pos = pos1 + 1; pos < pos2; pos++) {
			path[pathCounter] = new Point(getCol(), pos);
			if (point.y - getRow() == 0) 
				path[pathCounter] = new Point(pos, getRow());
			pathCounter++;
		}
		return path;
	}
} //end Cannon class
