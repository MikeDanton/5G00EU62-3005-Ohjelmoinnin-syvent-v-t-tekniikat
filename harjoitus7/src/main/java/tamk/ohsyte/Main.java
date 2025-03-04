package tamk.ohsyte;

import java.time.MonthDay;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventManager manager = EventManager.getInstance();

        // Add event providers
        manager.addEventProvider(new CSVEventProvider("events.csv"));

        // Test filters
        MonthDay today = MonthDay.now();
        Category testCategory = new Category("Holiday");

        System.out.println("Events on today's date:");
        List<Event> dateFiltered = manager.getFilteredEvents(new DateFilter(today));
        for (Event e : dateFiltered) {
            System.out.println(e);
        }

        System.out.println("\nEvents in category 'Holiday':");
        List<Event> categoryFiltered = manager.getFilteredEvents(new CategoryFilter(testCategory));
        for (Event e : categoryFiltered) {
            System.out.println(e);
        }

        System.out.println("\nEvents on today's date in category 'Holiday':");
        List<Event> dateCategoryFiltered = manager.getFilteredEvents(new DateCategoryFilter(today, testCategory));
        for (Event e : dateCategoryFiltered) {
            System.out.println(e);
        }
    }
}
