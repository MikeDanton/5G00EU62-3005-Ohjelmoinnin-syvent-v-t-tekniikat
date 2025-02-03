import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Today {
    private final List<Event> events;

    public Today() {
        this.events = new ArrayList<>();
        loadEvents();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Today --MM-DD category");
            System.exit(1);
        }

        String dateInput = args[0];
        String categoryInput = args[1];

        if (!dateInput.matches("--\\d{2}-\\d{2}")) {
            System.err.println("Invalid date format. Use --MM-DD (e.g., --01-31)");
            System.exit(1);
        }

        LocalDate filterDate;
        try {
            filterDate = LocalDate.parse(LocalDate.now().getYear() + dateInput.substring(1));
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            System.exit(1);
            return;
        }

        Category filterCategory;
        try {
            filterCategory = Category.parse(categoryInput);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid category format: " + e.getMessage());
            System.exit(1);
            return;
        }

        Today app = new Today();
        app.findAndPrintEvents(filterDate, filterCategory);
    }

    private void loadEvents() {
        events.add(new Event(LocalDate.of(2023, 9, 19), "Java SE 21 released", new Category("oracle", "java")));
        events.add(new Event(LocalDate.of(2022, 9, 20), "Java SE 19 released", new Category("oracle", "java")));
        events.add(new Event(LocalDate.of(2021, 10, 25), "macOS 12 Monterey released", new Category("apple", "macos")));
        events.add(new Event(LocalDate.of(2020, 11, 12), "macOS 11 Big Sur released", new Category("apple", "macos")));
        events.add(new Event(LocalDate.of(2019, 9, 17), "Java SE 13 released", new Category("oracle", "java")));
    }

    private void findAndPrintEvents(LocalDate filterDate, Category filterCategory) {
        List<Event> matchingEvents = new ArrayList<>();
        for (Event event : events) {
            if (isSameDay(event.getDate(), filterDate) && matchesCategory(event.getCategory(), filterCategory)) {
                matchingEvents.add(event);
            }
        }

        if (matchingEvents.isEmpty()) {
            System.out.println("No events found for the given date and category.");
            return;
        }

        matchingEvents.sort(Collections.reverseOrder());
        for (Event event : matchingEvents) {
            System.out.println(event.getDate().getYear() + ": " + event.getDescription());
        }
    }

    private boolean isSameDay(LocalDate eventDate, LocalDate filterDate) {
        return eventDate.getMonth() == filterDate.getMonth() && eventDate.getDayOfMonth() == filterDate.getDayOfMonth();
    }

    private boolean matchesCategory(Category eventCategory, Category filterCategory) {
        return filterCategory.getSecondary() == null
                ? eventCategory.getPrimary().equals(filterCategory.getPrimary())
                : eventCategory.equals(filterCategory);
    }
}
