package kata.supermarket;

import java.util.Objects;

public class ProductIdentifier {

    private String name;
    private String catagory;

    public ProductIdentifier(String name, String catagory) {
        this.name = name;
        this.catagory = catagory;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProductIdentifier))
            return false;
        ProductIdentifier that = (ProductIdentifier) o;
        return name.equals(that.name) &&
                        catagory.equals(that.catagory);
    }

    @Override public int hashCode() {
        return Objects.hash(name, catagory);
    }
}
