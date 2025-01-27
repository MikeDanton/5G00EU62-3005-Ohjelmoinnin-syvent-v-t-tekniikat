import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class EventProcessor {
    public static void main(String[] args) {

        Category macosCategory = new Category("apple", "macos");

        Event[] events = {
                new Event(LocalDate.of(2024, 9, 16), "macOS 15 Sequoia released", macosCategory),
                new Event(LocalDate.of(2023, 9, 26), "macOS 14 Sonoma released", macosCategory),
                new Event(LocalDate.of(2022, 10, 24), "macOS 13 Ventura released", macosCategory),
                new Event(LocalDate.of(2021, 10, 25), "macOS 12 Monterey released", macosCategory),
                new Event(LocalDate.of(2020, 11, 12), "macOS 11 Big Sur released", macosCategory)
        };

        // Print each event in the specified format
        for (Event event : events) {
            LocalDate date = event.getDate();
            String description = event.getDescription();
            String version = description.split(" ")[1]; // e.g., "15"
            String name = description.substring(description.indexOf(" ", description.indexOf(" ") + 1) + 1, description.lastIndexOf(" released"));
            String weekday = date.format(DateTimeFormatter.ofPattern("EEEE")); // Directly get "Monday", "Tuesday", etc.

            System.out.printf("macOS %s %s was released on a %s%n", version, name, weekday);
        }

        // Extract operating system names and sort alphabetically
        String[] osNames = new String[events.length];
        for (int i = 0; i < events.length; i++) {
            String description = events[i].getDescription();
            osNames[i] = description.substring(description.indexOf(" ", description.indexOf(" ") + 1) + 1, description.lastIndexOf(" released")); // Extract full name
        }

        Arrays.sort(osNames); // Sort alphabetically
        System.out.println("In alphabetical order: " + Arrays.toString(osNames));
    }
}
