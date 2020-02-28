# Notes

## Overview
The Discount Calculator holds information about the UnitProduct and WeightProduct discounts and can apply them
to the basket to calculate the total discount. Applying discounts to the whole basket allows support for a broad
range of rules including those that offer multi-product deals e.g. "meal deals". The DiscountCalculator is not
a component of the Basket because Calculating discounts is not the responsibility of the Basket.

The Product has an identifier which can be used by the Discount Calculator to select Products from the basket.

## TODO

DiscountRuleMultiBuyTwoForOne should be generalized into a multi-buy rule to support other discounts
e.g. 3 for 2 rules.

Create Classes for other DiscountRule types.

Write a more generalize ProductCriteria for selecting Items from the Basket.

Having the DiscountCalculator ask the Basket for a price was a mistake, if DiscountRuleMultiBuyTwoForOne had used
UnitProduct in the rule instead of ProductIdentifier then it would have known the price.
To support the broadest range of rules the DiscountCalculator needs to access Items from the Basket.
The Basket needs to support returning matching Items to support other rule types. eg. 10% off vegetables.

The basket internally could better support the calls make by the DiscountCalculator by storing items in a
more structured way. Completely separate Unit and Weight items internally at least.

The discount calculator needs to track how items are used in discounts to prevent discounts applying to multiple times.