package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountCalculator {
    private final List<DiscountRule> discounts;

    DiscountCalculator() {
        discounts = new ArrayList<>();
    }

    public void add(DiscountRule rule) {
        discounts.add(rule);
    }

    public BigDecimal calculate(Basket basket) {
        return discounts.stream().map( discountRule -> discountRule.calculate(basket)).reduce(
                        BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
                        .setScale(2, RoundingMode.HALF_UP);
    }
}
