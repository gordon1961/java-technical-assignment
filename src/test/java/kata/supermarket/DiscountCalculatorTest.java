package kata.supermarket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class DiscountCalculatorTest {

    private static ProductIdentifier DIGESTIVES_PRODUCT = new ProductIdentifier("digestives","biscuits");
    private static ProductIdentifier OREO_PRODUCT = new ProductIdentifier("oreso","biscuits");
    private static Basket testBasket;

    @BeforeAll
    public static void setup() {
        testBasket = new Basket();
        testBasket.add(aPackOfDigestives());
        testBasket.add(aPackOfDigestives());
    }

    private static Item aPackOfDigestives() {
        return new UnitProduct(new BigDecimal("1.55"),DIGESTIVES_PRODUCT).oneOf();
    }

    @Test
    public void discountRuleMultBuyTwoForOne() {
        DiscountCalculator calculator = new DiscountCalculator();
        calculator.add(new DiscountRuleMultiBuyTwoForOne(DIGESTIVES_PRODUCT,2));
        assertEquals(new BigDecimal("1.55").setScale(2, RoundingMode.HALF_UP), calculator.calculate(testBasket));
    }

    @Test
    public void discountRuleMultBuyTwoForOneDoesntAppy() {
        DiscountCalculator calculator = new DiscountCalculator();
        calculator.add(new DiscountRuleMultiBuyTwoForOne(OREO_PRODUCT,2));
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), calculator.calculate(testBasket));
    }

    @Test
    public void caclulatorReturnsZeroWithNoRules() {
        DiscountCalculator calculator = new DiscountCalculator();
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), calculator.calculate(testBasket));
    }

}
