package generator;
import java.awt.Point;
import javax.swing.JButton;

class Button extends JButton {
	private static final long serialVersionUID = 1L;
	Point point;
	String name = "Button";

	Button(Point point) { 		
		super();
		this.point = point;
	}
}
