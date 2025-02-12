import java.util.Objects;

public class Category {
    private final String primary;
    private final String secondary;

    public Category(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return secondary;
    }

    @Override
    public String toString() {
        return (secondary != null) ? primary + "/" + secondary : primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return Objects.equals(primary, that.primary) && Objects.equals(secondary, that.secondary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primary, secondary);
    }

    // Static factory method for parsing categories from strings
    public static Category parse(String categoryString) {
        if (categoryString == null || categoryString.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid category string");
        }

        String[] parts = categoryString.split("/");
        String primary = parts[0].trim().toLowerCase();
        String secondary = (parts.length == 2) ? parts[1].trim().toLowerCase() : null;

        return new Category(primary, secondary);
    }
}