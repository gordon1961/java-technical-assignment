package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private static final ProductIdentifier BEANS_PRODUCT = new ProductIdentifier("beans","tin");
    private static final ProductIdentifier MILK_PRODUCT  = new ProductIdentifier("milk","dairy");

    @Test
    void singleItemHasExpectedUnitPriceFromUnitProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new UnitProduct(price,BEANS_PRODUCT).oneOf().price());
    }

    @Test
    void productIdentifierInUnitProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(BEANS_PRODUCT, new UnitProduct(price,BEANS_PRODUCT).oneOf().productIdentifier());
    }


    @Test
    void productIdentifierComparisonInUnitProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertNotEquals(MILK_PRODUCT, new UnitProduct(price,BEANS_PRODUCT).oneOf().productIdentifier());
    }
}