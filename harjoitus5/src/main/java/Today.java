import java.util.List;

public class Today {
    public static void main(String[] args) {
        EventProvider provider = new CSVEventProvider("events.csv");
        List<Event> events = provider.getEvents();

        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        System.out.println("\n=== Events from CSV ===\n");
        for (Event event : events) {
            System.out.println(event);
        }
    }
}
