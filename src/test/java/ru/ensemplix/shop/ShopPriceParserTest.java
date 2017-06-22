package ru.ensemplix.shop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.ensemplix.shop.ShopPrice.Result.*;

public class ShopPriceParserTest {

    @Test
    public void testParseEmptyPrice() {
        assertEquals(NO_PRICE, ShopPriceParser.parse("").getResult());
    }

    @Test
    public void testParseNoPrice() {
        assertEquals(NO_PRICE, ShopPriceParser.parse("10 10").getResult());
    }

    @Test
    public void testParseNoSecondPrice() {
        assertEquals(INCORRECT_PRICE, ShopPriceParser.parse("К100:").getResult());
    }

    @Test
    public void testParseIncorrectPrice() {
        assertEquals(INCORRECT_PRICE, ShopPriceParser.parse("Кит").getResult());
    }

    @Test
    public void testParseIncorrectSecondPrice() {
        assertEquals(INCORRECT_PRICE, ShopPriceParser.parse("К150:Пит").getResult());
    }

    @Test
    public void testParseBuyPriceMustBeLeft() {
        assertEquals(BUY_MUST_BE_LEFT, ShopPriceParser.parse("П100:К150").getResult());
    }

    @Test
    public void testParseSellPriceMustBeRight() {
        assertEquals(SELL_MUST_BE_RIGHT, ShopPriceParser.parse("К100:К150").getResult());
    }

    @Test
    public void testParseSinglePriceSuccess() {
        assertEquals(SUCCESS, ShopPriceParser.parse("К60").getResult());
    }

    @Test
    public void testParseDoublePriceSuccess() {
        assertEquals(SUCCESS, ShopPriceParser.parse("К60:П15").getResult());
    }

}
