import java.util.Objects;

public class Category {
    private String primary;
    private String secondary;

    public Category(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return this.primary;
    }

    public String getSecondary() {
        return this.secondary;
    }

    @Override
    public String toString() {
        return String.format("%s/%s", this.primary, this.secondary);
    }

    @Override
    public boolean equals(Object o) {
        // Identical references?
        if (o == this) return true;

        // Correct type and non-null?
        if (!(o instanceof Category)) return false;

        // Cast to our type:
        Category that = (Category) o;

        if (this.primary.equals(that.primary) && 
            this.secondary.equals(that.secondary)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.primary, this.secondary);
    }    
}
