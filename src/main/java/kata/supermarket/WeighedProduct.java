package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final ProductIdentifier productIdentifier;

    public WeighedProduct(final BigDecimal pricePerKilo, ProductIdentifier productIdentifier) {
        this.pricePerKilo = pricePerKilo;
        this.productIdentifier = productIdentifier;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public ProductIdentifier getProductIdentifier() {
        return productIdentifier;
    }
}
