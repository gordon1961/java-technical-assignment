package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountRuleMultiBuyTwoForOne implements DiscountRule {

    private ProductIdentifier productIdentifier;
    private int quantity;

    public DiscountRuleMultiBuyTwoForOne(ProductIdentifier productIdentifier, int quantity) {
        this.productIdentifier = productIdentifier;
        this.quantity = quantity;
    }

    @Override public BigDecimal calculate(Basket basket) {
        long itemCount = basket.getProductUnits(productIdentifier);
        long discounts = itemCount/quantity;
        return basket.getUnitPrice(productIdentifier).multiply(new BigDecimal(discounts)).setScale(2, RoundingMode.HALF_UP);
    }
}
