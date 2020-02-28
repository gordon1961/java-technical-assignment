package kata.supermarket;

import java.math.BigDecimal;

public interface DiscountRule {
    BigDecimal calculate(Basket basket);
}
