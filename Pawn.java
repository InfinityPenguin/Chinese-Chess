package generator;
import static generator.Color.*;

import java.awt.*;
import javax.swing.ImageIcon;

	public class Pawn extends Piece {
		public Pawn(Point point, Color color, Game game) {
			super(point, color, game);
			name = "Pawn";
			icon = new ImageIcon("src/BPawn.gif");
			if (color == RED) 
				icon = new ImageIcon("src/RPawn.gif");
		}

		protected boolean canReachTarget(Point point) {
			int rowDif = getRow() - point.y;
			if ((rowDif == 1 && getColor().equals(BLACK) || 
					rowDif == -1 && getColor().equals(RED)) && point.x == getCol()) 
				return true;
			return hasCrossedRiver() && 
					Math.abs(point.x - getCol()) == 1 && 
					point.y == getRow();
		}
		

		@Override
		protected Point[] pathConstructor(Point point) {
			Point[] path = new Point[0];
			return path;
		}

		private boolean hasCrossedRiver() {
			if (color.equals(RED)) 
				return getRow() > 4;
			return getRow() < 5;
		}
	} //end Pawn class
