package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    public BigDecimal getProductWeight(ProductIdentifier product) {
        return items().stream().filter(item ->item instanceof ItemByWeight)
                        .map( item -> ((ItemByWeight)item))
                        .filter( item -> product.equals(item.productIdentifier()))
                        .map(ItemByWeight::getWeightInKilos)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO);
    }

    public long getProductUnits(ProductIdentifier product) {
        return items().stream().filter(item -> item instanceof ItemByUnit)
                        .map(item -> ((ItemByUnit) item))
                        .filter(item -> product.equals(item.productIdentifier()))
                        .count();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            return BigDecimal.ZERO;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
