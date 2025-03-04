import java.util.*;

public class EventManager {
    private static final EventManager INSTANCE = new EventManager();
    private final Map<String, EventProvider> eventProviders;

    private EventManager() {
        this.eventProviders = new HashMap<>();
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }

    public boolean addEventProvider(EventProvider provider) {
        String id = provider.getIdentifier();
        if (eventProviders.containsKey(id)) {
            return false; // Provider is already added
        }
        eventProviders.put(id, provider);
        return true;
    }

    public boolean removeEventProvider(String providerId) {
        return eventProviders.remove(providerId) != null;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        for (EventProvider provider : eventProviders.values()) {
            events.addAll(provider.getEvents());
        }
        events.sort(Collections.reverseOrder()); // Sort descending
        return events;
    }

    public int getEventProviderCount() {
        return eventProviders.size();
    }

    public List<String> getEventProviderIdentifiers() {
        return new ArrayList<>(eventProviders.keySet());
    }
}

/*
// EventManager (Using ArrayList)
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager {
    private static final EventManager INSTANCE = new EventManager();
    private final List<EventProvider> eventProviders;

    private EventManager() {
        this.eventProviders = new ArrayList<>();
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }

    public boolean addEventProvider(EventProvider provider) {
        // Prevent duplicates by checking existing identifiers
        for (EventProvider existingProvider : eventProviders) {
            if (existingProvider.getIdentifier().equals(provider.getIdentifier())) {
                return false; // Duplicate, don't add it
            }
        }
        eventProviders.add(provider);
        return true;
    }

    public boolean removeEventProvider(String providerId) {
        for (EventProvider provider : eventProviders) {
            if (provider.getIdentifier().equals(providerId)) {
                eventProviders.remove(provider);
                return true; // Successfully removed
            }
        }
        return false; // Not found
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        for (EventProvider provider : eventProviders) {
            events.addAll(provider.getEvents());
        }
        events.sort(Collections.reverseOrder()); // Sort descending
        return events;
    }

    public int getEventProviderCount() {
        return eventProviders.size();
    }

    public List<String> getEventProviderIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        for (EventProvider provider : eventProviders) {
            identifiers.add(provider.getIdentifier());
        }
        return identifiers;
    }
}


 */