package generator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	class Board extends JPanel implements ActionListener {
		private static final long serialVersionUID = 1L;
		private ImageIcon rcar = new ImageIcon("src/RCar.gif");
		private ImageIcon rhorse = new ImageIcon("src/RHorse.gif");
		private ImageIcon relephant = new ImageIcon("src/RElephant.gif");
		private ImageIcon radvisor = new ImageIcon("src/RAdvisor.gif");
		private ImageIcon rking = new ImageIcon("src/RKing.gif");
		private ImageIcon rcannon = new ImageIcon("src/RCannon.gif");
		private ImageIcon rpawn = new ImageIcon("src/RPawn.gif");
		private ImageIcon bcar = new ImageIcon("src/BCar.gif");
		private ImageIcon bhorse = new ImageIcon("src/BHorse.gif");
		private ImageIcon belephant = new ImageIcon("src/BElephant.gif");
		private ImageIcon badvisor = new ImageIcon("src/BAdvisor.gif");
		private ImageIcon bking = new ImageIcon("src/BKing.gif");
		private ImageIcon bcannon = new ImageIcon("src/BCannon.gif");
		private ImageIcon bpawn = new ImageIcon("src/BPawn.gif");
		private Game game;
		
		Board(Game game) {
			this.game = game;
			game.setButtons(new Button[9][10]);
			setLayout(new GridLayout(10, 9));
			for (int row = 9; row >= 0; row--) 
				for (int col = 8; col >= 0; col--) {
					game.getButtons()[col][row] = new Button(new Point(col, row));
					game.getButtons()[col][row].addActionListener(this);
					add(game.getButtons()[col][row]);
				}
			setIcons();
		}
		
		void setIcons() {
			game.getButtons()[0][0].setIcon(rcar);
			game.getButtons()[1][0].setIcon(rhorse);
			game.getButtons()[2][0].setIcon(relephant);
			game.getButtons()[3][0].setIcon(radvisor);
			game.getButtons()[4][0].setIcon(rking);
			game.getButtons()[5][0].setIcon(radvisor);
			game.getButtons()[6][0].setIcon(relephant);
			game.getButtons()[7][0].setIcon(rhorse);
			game.getButtons()[8][0].setIcon(rcar);
			game.getButtons()[1][2].setIcon(rcannon);
			game.getButtons()[7][2].setIcon(rcannon);
			for (int col = 0; col < 9; col += 2) 
				game.getButtons()[col][3].setIcon(rpawn);
			game.getButtons()[0][9].setIcon(bcar);
			game.getButtons()[1][9].setIcon(bhorse);
			game.getButtons()[2][9].setIcon(belephant);
			game.getButtons()[3][9].setIcon(badvisor);
			game.getButtons()[4][9].setIcon(bking);
			game.getButtons()[5][9].setIcon(badvisor);	
			game.getButtons()[6][9].setIcon(belephant);
			game.getButtons()[7][9].setIcon(bhorse);
			game.getButtons()[8][9].setIcon(bcar);
			game.getButtons()[1][7].setIcon(bcannon);
			game.getButtons()[7][7].setIcon(bcannon);
			for (int col = 0; col < 9; col += 2) 
				game.getButtons()[col][6].setIcon(bpawn);
		}
		
		void resetIcons() {
			for (int row = 0; row < 10; row++) 
				for (int col = 0; col < 9; col++) 
					game.getButtons()[col][row].setIcon(null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Button button = (Button)e.getSource();
			game.setSelectedPoint(button.point);
		}
	} //end Board class
