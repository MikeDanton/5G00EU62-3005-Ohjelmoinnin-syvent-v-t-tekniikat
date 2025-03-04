import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;

public class CSVEventProvider implements EventProvider {
    private final Path csvFilePath;
    private final List<Event> events;
    private final String identifier;

    public CSVEventProvider(String fileName) {
        Path homeDir = Paths.get(System.getProperty("user.home"), ".today");
        this.csvFilePath = homeDir.resolve(fileName);
        this.identifier = fileName; // Use file name as a unique identifier
        this.events = new ArrayList<>();

        if (!Files.exists(csvFilePath)) {
            throw new IllegalArgumentException("CSV file not found: " + csvFilePath);
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
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(events); // Return a copy to prevent external modification
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

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
