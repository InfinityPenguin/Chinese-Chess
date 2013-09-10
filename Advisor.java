package generator;
import static generator.Color.RED;

import java.awt.Point;
import javax.swing.ImageIcon;

	public class Advisor extends Piece {
		public Advisor(Point point, Color color, Game game) {
			super(point, color, game);
			name = "Advisor";
			icon = new ImageIcon("src/BAdvisor.gif");
			if (color == RED) 
				icon = new ImageIcon("src/RAdvisor.gif");
		}

		protected boolean canReachTarget(Point point) {
			if (Math.abs(point.y - getRow()) == 1 && 
					Math.abs(point.x - getCol()) == 1) 
				return true;
			return false;
		}

		protected Point[] pathConstructor(Point point) {
			Point[] path = new Point[0];
			return path;
		}

		@Override
		boolean canLeavePalace() {
			return false;
		}
	} //end Advisor class
