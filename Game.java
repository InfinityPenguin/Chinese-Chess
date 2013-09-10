package generator;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import static generator.Color.*;

class Game { 
	private Player p1, p2, currentPlayer;
	private boolean gameOver;
	private Piece[][] board; 
	private Button[][] buttonHolder;
	private Point selectedPoint;
	private Board gui;
	
	void Initialize() {
		gameOver = false;
		board = new Piece[9][10];
		p1 = new Player("Player 1", RED, this);
		p2 = new Player("Player 2", BLACK, this);
		currentPlayer = p1;
		setPieces();
		allocatePieces();
		gui.resetIcons();
		gui.setIcons();
	}
	
	Game() {
		gui = new Board(this);
		setUpGUI();
	}
	
	void setPieces() {
		board[0][0] = new Car(new Point(0, 0), RED, this);
		board[1][0] = new Horse(new Point(1, 0), RED, this);
		board[2][0] = new Elephant(new Point(2, 0), RED, this);
		board[3][0] = new Advisor(new Point(3, 0), RED, this);
		board[4][0] = new King(new Point(4, 0), RED, this);
		board[5][0] = new Advisor(new Point(5, 0), RED, this);
		board[6][0] = new Elephant(new Point(6, 0), RED, this);
		board[7][0] = new Horse(new Point(7, 0), RED, this);
		board[8][0] = new Car(new Point(8, 0), RED, this);
		board[1][2] = new Cannon(new Point(1, 2), RED, this);
		board[7][2] = new Cannon(new Point(7, 2), RED, this);
		for (int col = 0; col < 9; col += 2) 
			board[col][3] = new Pawn(new Point(col, 3), RED, this);
		board[0][9] = new Car(new Point(0, 9), BLACK, this);
		board[1][9] = new Horse(new Point(1, 9), BLACK, this);
		board[2][9] = new Elephant(new Point(2, 9), BLACK, this);
		board[3][9] = new Advisor(new Point(3, 9), BLACK, this);
		board[4][9] = new King(new Point(4, 9), BLACK, this);
		board[5][9] = new Advisor(new Point(5, 9), BLACK, this);
		board[6][9] = new Elephant(new Point(6, 9), BLACK, this);
		board[7][9] = new Horse(new Point(7, 9), BLACK, this);
		board[8][9] = new Car(new Point(8, 9), BLACK, this);
		board[1][7] = new Cannon(new Point(1, 7), BLACK, this);
		board[7][7] = new Cannon(new Point(7, 7), BLACK, this);
		for (int col = 0; col < 9; col += 2) 
			board[col][6] = new Pawn(new Point(col, 6), BLACK, this);
	}

	void setUpGUI() {
		JFrame frame = new JFrame();
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setTitle("Chinese Chess - Elite version");
	}

	void play() {
		while (!gameOver) {
			currentPlayer.takeTurn();
			currentPlayer = getOtherPlayer(currentPlayer);
		}
		victory(currentPlayer);
	}

	void allocatePiece(Piece piece, Player player) {
		piece.setPlayer(player);
		player.addPiece(piece);
		if (piece.isKing()) 
			player.setKing(piece);
	}
	
	void allocatePieces() {
		for (int row = 0; row < 10; row++) 
			for (int col = 0; col < 9; col++) {
				Piece piece = getPiece(new Point(col, row));
				if (piece != null) {
					if (piece.getColor().equals(RED)) 
						allocatePiece(piece, p1);
					else 
						allocatePiece(piece, p2);
				}
			}
	}
	
	void victory(Player player) {
		System.out.println(player.getName() + " has won!");
	}

	Player getOtherPlayer(Player player) {
		if (player.equals(p1)) 
			return p2;
		return p1;
	}
	
	Button[][] getButtons() {
		return buttonHolder;
	}
	
	void setButtons(Button[][] buttons) {
		buttonHolder = buttons;
	}

	Piece getPiece(Point point) {
		return board[point.x][point.y];
	}

	void setPiece(Piece piece, Point point) {
		board[point.x][point.y] = piece;
		if (piece != null) 
			piece.setPoint(point);
	}

	public Point getSelectedPoint() {
		return selectedPoint;
	}

	public void setSelectedPoint(Point selectedPoint) {
		this.selectedPoint = selectedPoint;
	}

	void removePiece(Point point) {
		Piece piece = getPiece(point);
		piece.setPoint(new Point(20, 20));
		setPiece(null, point);
	}
	
	void eliminatePiece(Point point) {
		Piece target = getPiece(point);
		ArrayList<Piece> pieces = target.getPlayer().getPieces();
		for(int i = 0; i < pieces.size(); i++) 
			if (pieces.get(i).equals(target)) {
				pieces.remove(i);
				removePiece(point);
			}
		buttonHolder[point.x][point.y].setIcon(null);
	}	
	
	void setGameOver(Boolean bool) {
		gameOver = bool;
	}
} //end Game class


