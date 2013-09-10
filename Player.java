package generator;
import java.awt.Point;
import java.util.ArrayList;

class Player {
	private Color color;
	private String name;
	private ArrayList<Piece> ownedPieces = new ArrayList<Piece>();
	private Piece selectedPiece, king;
	private boolean finishedTurn;
	Game game;

	Player(String name, Color color, Game game) {
		this.name = name;
		this.color = color;
		this.game = game;
	} 

	public String toString() {
		return getName();
	}	

	void takeTurn() {
		if (checkMated()) game.setGameOver(true);
		else {
			System.out.println(name + "'s turn. ");
			while (selectedPiece == null) 
				selectPiece();
			while (!finishedTurn) 
				selectPiece();
			game.getButtons()[selectedPiece.getCol()][selectedPiece.getRow()].setIcon(selectedPiece.icon);
			selectedPiece = null; 
			finishedTurn = false;
		}
	}

	private boolean checkMated() {
		for(Piece piece : ownedPieces) 
			for(int row = 0; row < 10; row++) 
				for(int col = 0; col < 9; col++) {
					Piece target = game.getPiece(new Point(col, row));
					if (piece.canMoveTo(new Point(col, row))) 
						if (target == null || !(target.getColor().equals(getColor()))) 
							return false;
				}
		System.out.println(this + " has been checkmated!");
		return true;
	}

	private void selectPiece() {
		selectPosition();
		Piece piece = game.getPiece(game.getSelectedPoint());
		if (piece != null && color == piece.color) {
			System.out.println(game.getPiece(game.getSelectedPoint()) + " selected.");
			selectedPiece = piece; 
		} else {
			if (selectedPiece != null) finishedTurn = selectedPiece.move(game.getSelectedPoint());
		}
	}


	private void selectPosition() {
		game.setSelectedPoint(null);
		while (game.getSelectedPoint() == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}


	Color getColor() {
		return color;
	}

	String getName() {
		return name;
	}

	ArrayList<Piece> getPieces() {
		return ownedPieces;
	}

	void addPiece(Piece piece) {
		ownedPieces.add(piece);
	}

	void setKing(Piece piece) {
		king = piece;
	}

	Piece getKing() {
		return king;
	}
} //end Player class
