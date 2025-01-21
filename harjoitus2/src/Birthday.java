import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Birthday {
    public static void main(String[] args) {
        // Try to read the BIRTHDATE environment variable
        String birthdateEnv = System.getenv("BIRTHDATE");

        if (birthdateEnv == null || birthdateEnv.isEmpty()) {
            System.out.println("Please set the BIRTHDATE environment variable in YYYY-MM-DD format.");
            return;
        }

        try {
            // Convert the birthday to a LocalDate object
            LocalDate birthdate = LocalDate.parse(birthdateEnv);
            LocalDate today = LocalDate.now();

            // Check if today is the user's birthday
            if (birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
                System.out.println("Happy Birthday!");
            }

            // Calculate the difference in days between the birthday and today
            long daysBetween = ChronoUnit.DAYS.between(birthdate, today);

            if (daysBetween < 0) {
                System.out.println("Your birthday is in the future. Please check the BIRTHDATE value.");
            } else {
                System.out.println("You are " + daysBetween + " days old.");

                // Check if the number of days is divisible by one thousand
                if (daysBetween % 1000 == 0) {
                    System.out.println("That's a nice round number!");
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD for the BIRTHDATE environment variable.");
        }
    }
}
