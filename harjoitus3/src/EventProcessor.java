import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

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

        for (Event event : events) {
            LocalDate date = event.getDate();
            String description = event.getDescription();

            // Split description more cleanly: "macOS <version> <name> released"
            String[] parts = description.split(" ", 4); // Ensure "Big Sur" stays together
            String version = parts[1]; // macOS version number
            String name = parts[2] + (parts.length > 3 ? " " + parts[3].replace(" released", "") : ""); // Keep multi-word names intact

            // Print using simpler weekday formatting and lowercase conversion
            System.out.printf(Locale.US, "macOS %s %s was released on a %s%n",
                    version, name, String.format(Locale.US, "%tA", date).toLowerCase());
        }

        // Extract OS names using a simpler approach
        String[] osNames = Arrays.stream(events)
                .map(e -> e.getDescription().replaceAll("macOS \\d+ ", "").replace(" released", ""))
                .sorted()
                .toArray(String[]::new);

        System.out.println("In alphabetical order: " + Arrays.toString(osNames));
    }
}
