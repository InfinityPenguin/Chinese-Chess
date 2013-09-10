package generator;
import static generator.Color.RED;
import java.awt.Point;
import javax.swing.ImageIcon;

abstract class Piece {
		protected Point point;
		protected Color color;
		protected Player player;
		protected String name;
		protected Game game;
		ImageIcon icon;

		Piece(Point point, Color color, Game game) {
			this.point = point;
			this.color = color;
			this.game = game;
		}

		public String toString() {
			return new String(color.name() + " " + name + " at " + "[" + point.x + ", " + point.y + "]");
		}

		protected abstract boolean canReachTarget(Point point);
		protected abstract Point[] pathConstructor(Point point);

		protected boolean canMoveTo(Point point) {
			if (getPoint().equals(point)) 
				return false; 
			return canReachTarget(point) && 
					pathClear(pathConstructor(point)) && 
					!wouldEndangerKing(point) && 
					checkRiver(point.y) && 
					checkPalace(point);
		}

		protected boolean pathClear(Point[] path) {
			for(Point point : path)	
				if (game.getPiece(point) != null) 
					return false;
			return true;
		}

		protected boolean wouldEndangerKing(Point point) {
			Piece target = game.getPiece(point);
			Point temp = new Point(getCol(), getRow());
			moveHelper(point, false);
			for(Piece piece : game.getOtherPlayer(player).getPieces()) 
				if (piece != null && piece.canAttackKing(player.getKing())) {
					game.removePiece(point);
					game.setPiece(this, temp);
					game.setPiece(target, point);
					return true;
				}
			game.removePiece(point);
			game.setPiece(this, temp);
			game.setPiece(target, point);
			return false;
		}

		protected boolean canAttackKing(Piece king) {
			return canReachTarget(king.point) && 
					pathClear(pathConstructor(king.point));
		}

		boolean move(Point point) {
			if (canMoveTo(point)) {
				moveHelper(point, true);
				return true;
			}
			return false;
		}

		protected void moveHelper(Point point, boolean eliminate) {
			Piece target = game.getPiece(point);
			if (eliminate) 
				game.getButtons()[getCol()][getRow()].setIcon(null);
			game.removePiece(getPoint());
			if (target != null) {			
				if (eliminate) {
					System.out.println("Captured " + target);
					game.eliminatePiece(point);
				}
				else 
					game.removePiece(point);
				game.setPiece(this, point);
			}
			else {
				game.setPiece(this, point);
				if (eliminate) 
					System.out.println("Moved to [" + getCol() + ", " + getRow() + "]");
			}
		}

		Player getPlayer() {
			return player;
		}

		void setPlayer(Player player) {
			this.player = player;
		}

		Color getColor() {
			return color;
		}

		int getRow() {
			return point.y;
		}

		int getCol() {
			return point.x;
		}

		Point getPoint() {
			return point;
		}

		void setPoint(Point point) {
			this.point = new Point(point);
		}

		void setRow(int row) {
			setPoint(new Point(getCol(), row));
		}

		void setCol(int col) {
			setPoint(new Point(col, getRow()));
		}
		
		ImageIcon getIcon() {
			return icon;
		}

		boolean isKing() {
			return false;
		}

		boolean canLeavePalace() {
			return true;
		}

		boolean canCrossRivers() {
			return true;
		}

		boolean checkRiver(int row) {
			if (!canCrossRivers()) {
				if (color.equals(RED)) 
					return row < 5;
				return row > 4;
			}
			return true;
		}

		boolean checkPalace(Point point) {
			if (!canLeavePalace()) {
				if (point.x <= 5 && point.x >= 3) {
					if (color.equals(RED)) 
						return point.y <= 2;
					return point.y >= 7;
				} return false;
			}
			return true;
		}
	} //end Piece class
