import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int targetNumber = (int) (Math.random() * 100) + 1; // Random number between 1-100
        int attempts = 7;
        boolean guessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have 7 attempts to guess the number (between 1 and 100).");

        while (attempts > 0 && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number!");
                guessedCorrectly = true;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }

            attempts--;
            if (attempts > 0 && !guessedCorrectly) {
                System.out.println("Attempts left: " + attempts);
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The number was: " + targetNumber);
        }

        scanner.close();
    }
}
