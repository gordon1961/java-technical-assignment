package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    private static ProductIdentifier MILK_PRODUCT = new ProductIdentifier("milk","dairy");
    private static ProductIdentifier DIGESTIVES_PRODUCT = new ProductIdentifier("digestives","biscuits");
    private static ProductIdentifier AMERICAN_SWEETS_PRODUCT = new ProductIdentifier("american sweets","sweets");
    private static ProductIdentifier PICK_AND_MIX_PRODUCT = new ProductIdentifier("pick and mix","sweets");

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new UnitProduct(new BigDecimal("0.49"),MILK_PRODUCT).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new UnitProduct(new BigDecimal("1.55"),DIGESTIVES_PRODUCT).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"),AMERICAN_SWEETS_PRODUCT);
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"),PICK_AND_MIX_PRODUCT);
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    @Test
    void basketReturnsZeroForNoUnitProducts() {
        assertEquals(0, new Basket().getProductUnits(MILK_PRODUCT));
    }

    @Test
    void basketReturnsZeroForNoWeightedProducts() {
        assertEquals(BigDecimal.ZERO, new Basket().getProductWeight(MILK_PRODUCT));
    }

    @Test
    void basketCountsUnitProducts() {
            Basket basket = new Basket();
            basket.add(aPackOfDigestives());
            basket.add(aPackOfDigestives());
            assertEquals(2, basket.getProductUnits(DIGESTIVES_PRODUCT));
    }


    @Test
    void basketWeightsProducts() {
        Basket basket = new Basket();
        basket.add(twoFiftyGramsOfAmericanSweets());
        basket.add(twoFiftyGramsOfAmericanSweets());
        BigDecimal result = basket.getProductWeight(AMERICAN_SWEETS_PRODUCT);
        assertEquals(new BigDecimal(".5").setScale(2, RoundingMode.HALF_UP),
                        basket.getProductWeight(AMERICAN_SWEETS_PRODUCT).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void basketUnitPrice() {
        Basket basket = new Basket();
        basket.add(aPackOfDigestives());
        basket.add(aPackOfDigestives());
        assertEquals(new BigDecimal("1.55"), basket.getUnitPrice(DIGESTIVES_PRODUCT));
    }
}