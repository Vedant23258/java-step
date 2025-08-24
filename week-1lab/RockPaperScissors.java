 import java.util.Scanner;
 public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of games: ");
        int games = scanner.nextInt();
        scanner.nextLine();
        String[] choices = {"rock", "paper", "scissors"};
        String[][] results = new String[games][3];
        int playerWins = 0, computerWins = 0;
        for (int i = 0; i < games; i++) {
            System.out.print("Enter your choice (rock/paper/scissors) for game " + (i + 1) + ": ");
            String playerChoice = scanner.nextLine().toLowerCase();
            String computerChoice = getComputerChoice();
            String winner = findWinner(playerChoice, computerChoice);
            if (winner.equals("player")) playerWins++;
            else if (winner.equals("computer")) computerWins++;
            results[i][0] = playerChoice;
            results[i][1] = computerChoice;
            results[i][2] = winner;
        }
        String[][] summary = getStats(results, playerWins, computerWins, games);
        displayResults(results, summary);
    }
    public static String getComputerChoice() {
        int r = (int)(Math.random() * 3);
        if (r == 0) return "rock";
        else if (r == 1) return "paper";
        else return "scissors";
    }
    public static String findWinner(String player, String computer) {
        if (player.equals(computer)) return "draw";
        if (player.equals("rock") && computer.equals("scissors")) return "player";
        if (player.equals("scissors") && computer.equals("paper")) return "player";
        if (player.equals("paper") && computer.equals("rock")) return "player";
        if (computer.equals("rock") && player.equals("scissors")) return "computer";
        if (computer.equals("scissors") && player.equals("paper")) return "computer";
        if (computer.equals("paper") && player.equals("rock")) return "computer";
        return "invalid";
    }
    public static String[][] getStats(String[][] results, int playerWins, int computerWins, int total) {
        String[][] stats = new String[3][2];
        stats[0][0] = "Player Wins";
        stats[0][1] = String.valueOf(playerWins);
        stats[1][0] = "Computer Wins";
        stats[1][1] = String.valueOf(computerWins);
        stats[2][0] = "Player Win %";
        stats[2][1] = String.format("%.2f", (playerWins * 100.0) / total);
        return stats;
    }
    public static void displayResults(String[][] results, String[][] stats) {
        System.out.println("Game\tPlayer\tComputer\tWinner");
        for (int i = 0; i < results.length; i++) {
            System.out.println((i + 1) + "\t" + results[i][0] + "\t" + results[i][1] + "\t\t" + results[i][2]);
        }
        System.out.println("\nSummary:");
        for (int i = 0; i < stats.length; i++) {
            System.out.println(stats[i][0] + ": " + stats[i][1]);
        }
    }
 }