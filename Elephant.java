package generator;
import static generator.Color.*;

import java.awt.*;
import javax.swing.ImageIcon;

	public class Elephant extends Piece {
		public Elephant(Point point, Color color, Game game) {
			super(point, color, game);
			name = "Elephant";
			icon = new ImageIcon("src/BElephant.gif");
			if (color == RED) 
				icon = new ImageIcon("src/RElephant.gif");
		}

		protected boolean canReachTarget(Point point) {
			if (Math.abs(point.y - getRow()) == 2 && 
					Math.abs(point.x - getCol()) == 2) 
				return true;
			return false;
		}

		protected Point[] pathConstructor(Point point) {
			int x = -1; int y = -1;
			if (point.x - getCol() > 0) 
				x = 1;
			if (point.y - getRow() > 0) 
				y = 1;
			Point[] path = new Point[1];
			path[0] = new Point(getCol() + x, getRow() + y);
			return path;
		}

		@Override
		boolean canCrossRivers() {
			return false;
		}
	} //end Elephant class
