package generator;
import java.util.Scanner;

class Utility {
	static boolean ask(String prompt) {
		while (true) {
			System.out.print(prompt);
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (input.equals("y") || input.equals("Y")) 
				return true;
			if (input.equals("n") || input.equals("N")) 
				return false;
			System.out.println("Please answer y or n.");
		}
	}
}
