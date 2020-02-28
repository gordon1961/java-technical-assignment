package kata.supermarket;

import java.math.BigDecimal;

public class UnitProduct {

    private final BigDecimal pricePerUnit;
    private final ProductIdentifier productIdentifier;

    public UnitProduct(final BigDecimal pricePerUnit, ProductIdentifier productIdentifier) {
        this.pricePerUnit = pricePerUnit;
        this.productIdentifier = productIdentifier;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public ProductIdentifier getProductIdentifier() {
        return productIdentifier;
    }
}
