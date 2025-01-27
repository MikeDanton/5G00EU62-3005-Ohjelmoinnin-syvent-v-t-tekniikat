import java.time.LocalDate;
import java.util.Objects;

public class Event {
    private LocalDate date;
    private String description;
    private Category category;

    public Event(LocalDate date, String description, Category category) {
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return String.format(
            "%s: %s (%s)",
            this.date, this.description, this.category);
    }

    @Override
    public boolean equals(Object o) {
        // Identical references?
        if (o == this) return true;

        // Correct type and non-null?
        if (!(o instanceof Event)) return false;

        // Cast to our type:
        Event that = (Event) o;

        if (Objects.equals(this.date, that.date) &&
            Objects.equals(this.description, that.description) && 
            Objects.equals(this.category, that.category)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.date, this.description, this.category);
    }
}
