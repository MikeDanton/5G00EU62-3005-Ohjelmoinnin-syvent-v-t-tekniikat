import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;

public class CSVEventProvider implements EventProvider {
    private final Path csvFilePath;
    private final List<Event> events;

    public CSVEventProvider(String fileName) {
        Path homeDir = Paths.get(System.getProperty("user.home"), ".today");
        this.csvFilePath = homeDir.resolve(fileName);
        this.events = new ArrayList<>();

        if (!Files.exists(csvFilePath)) {
            System.err.println("Error: CSV file not found at " + csvFilePath);
            System.exit(1);
        }

        loadEvents(); // Load events on initialization
    }

    private void loadEvents() {
        try (Reader reader = Files.newBufferedReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                LocalDate date = LocalDate.parse(record.get("date"));
                String description = record.get("description");
                String primaryCategory = record.get("category");
                String secondaryCategory = record.isMapped("subcategory") ? record.get("subcategory") : null;

                events.add(new Event(date, description, new Category(primaryCategory, secondaryCategory)));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public List<Event> getEventsOfCategory(Category category) {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getCategory().equals(category)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    @Override
    public List<Event> getEventsOfDate(MonthDay monthDay) {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : events) {
            if (MonthDay.from(event.getDate()).equals(monthDay)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
}
