class GameLauncher {
  public static void main(String args[]) {
    while (true) {
      Game game = new Game();
      game.play();
      if (!Yes("Play again? (y/n) ")) break;
    }
  }
  
  private static boolean Yes(String prompt) {
    while (true) {
      System.out.print(prompt);
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      if (input.equals("y") || input.equals("Y")) return true;
      if (input.equals("n") || input.equals("N")) return false;
      System.out.println("Please enter y or n.");
    }
  }
}
