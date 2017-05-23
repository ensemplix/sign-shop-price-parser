package ru.ensemplix.shop;

/**
 * Результат парсинга строки на цены.
 */
public class ShopPrice {

    private final Result result;
    private final int buyPrice;
    private final int sellPrice;

    public ShopPrice(Result result) {
        this(result, -1, -1);
    }

    public ShopPrice(Result result, int buyPrice, int sellPrice) {
        this.result = result;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    /**
     * Возвращает результат парсинга строки на цену.
     *
     * @return Результат парсинга строки на цену.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Возвращает цену на покупку предмета из магазина.
     * Если возвращает -1, то цена не была предоставлена.
     *
     * @return Цена на покупку предмета из магазина.
     */
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * Возвращает цену на продажу предмета в магазин.
     * Если возвращает -1, то цена не была предоставлена.
     *
     * @return Цена на продажу предмета в магазин.
     */
    public int getSellPrice() {
        return sellPrice;
    }

    // Различные варианты ответа.
    public enum Result {
        // Не указана цена на товар.
        NO_PRICE,
        // Покупка товара должна находится слева.
        BUY_MUST_BE_LEFT,
        // Продажа товара должна находится справа.
        SELL_MUST_BE_RIGHT,
        // Неправильно введена цена на товар.
        INCORRECT_PRICE,
        // Цена на товар указана правильно.
        SUCCESS
    }

}
