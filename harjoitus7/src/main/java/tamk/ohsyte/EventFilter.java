package tamk.ohsyte;

import java.time.MonthDay;

/**
 * Abstract base class for event filters.
 */
public abstract class EventFilter {
    public abstract boolean accepts(Event event);
}

/**
 * Filters events by date.
 */
class DateFilter extends EventFilter {
    private MonthDay monthDay;
    private Integer year;

    public DateFilter(MonthDay monthDay) {
        this.monthDay = monthDay;
        this.year = null;
    }

    public DateFilter(MonthDay monthDay, int year) {
        this.monthDay = monthDay;
        this.year = year;
    }

    @Override
    public boolean accepts(Event event) {
        if (event instanceof SingularEvent) {
            SingularEvent singularEvent = (SingularEvent) event;
            return this.monthDay.equals(singularEvent.getMonthDay()) &&
                    (this.year == null || this.year == singularEvent.getYear());
        } else if (event instanceof AnnualEvent) {
            return this.monthDay.equals(event.getMonthDay());
        }
        return false;
    }
}

/**
 * Filters events by category.
 */
class CategoryFilter extends EventFilter {
    private Category category;

    public CategoryFilter(Category category) {
        this.category = category;
    }

    @Override
    public boolean accepts(Event event) {
        return this.category.equals(event.getCategory());
    }
}

/**
 * Filters events by both date and category.
 */
class DateCategoryFilter extends EventFilter {
    private DateFilter dateFilter;
    private CategoryFilter categoryFilter;

    public DateCategoryFilter(MonthDay monthDay, Category category) {
        this.dateFilter = new DateFilter(monthDay);
        this.categoryFilter = new CategoryFilter(category);
    }

    @Override
    public boolean accepts(Event event) {
        return dateFilter.accepts(event) && categoryFilter.accepts(event);
    }
}

/**
 * Filters events based on description content.
 */
class DescriptionFilter extends EventFilter {
    private String keyword;

    public DescriptionFilter(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean accepts(Event event) {
        return event.getDescription().toLowerCase().contains(keyword);
    }
}
