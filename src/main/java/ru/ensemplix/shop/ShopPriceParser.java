package ru.ensemplix.shop;

import static ru.ensemplix.shop.ShopPrice.Result.*;

/**
 * Вспомогательный класс для парсинга строки на цену продажи и покупки.
 * Все действия описываются от лица игрока, который решил торговать с магазином.
 */
public class ShopPriceParser {

    // Сокращение от "Купить", для обозначения покупки товара.
    private static final String BUY_PRICE_PREFIX = "К";
    // Сокращение от "Продать", для обозначения продажи товара.
    private static final String SELL_PRICE_PREFIX = "П";
    // Обозначение, которое используется для разделения цены.
    private static final String PRICE_SPLITTER = ":";

    /**
     * Парсит строку на цены для торговли.
     *
     * Цена может содержать в себе как цену для покупки, так и для продажи. Для обозначения
     * режима торговли используется специальный префикс перед ценной. Для "Купить" префикс
     * "К". Для "Продать" префикс "П". Для разделения цены используется символ ":". Обе цены
     * не обязательны. При двойном режиме орговли режим "Купить" обязательно должен находится
     * слева. Режим "Продать" должен находится справа. Пример правильно указанной цены при
     * двойном режиме: "К60:П15".
     *
     * @param price Строка, которую парсит на цену.
     * @return Результат парсинга строки на цены.
     */
    public static ShopPrice parse(String price) {
        if(price == null || price.isEmpty()) {
            return new ShopPrice(NO_PRICE);
        }

        boolean buyProvided = false, sellProvided = false;
        int buyPrice = -1, sellPrice = -1;

        // Цена содержит покупку и продажу.
        if(price.contains(":")) {
            String[] args = price.split(":");

            if(args.length == 1) {
                return new ShopPrice(INCORRECT_PRICE);
            }

            // Покупка товара должна быть слева.
            if(!args[0].startsWith(BUY_PRICE_PREFIX)) {
                return new ShopPrice(BUY_MUST_BE_LEFT);
            }

            // Продажа товара должна быть справа.
            if(!args[1].startsWith(SELL_PRICE_PREFIX)) {
                return new ShopPrice(SELL_MUST_BE_RIGHT);
            }

            buyPrice = getPrice(args[0]);
            sellPrice = getPrice(args[1]);
            buyProvided = true;
            sellProvided = true;
        } else if(price.startsWith(BUY_PRICE_PREFIX)) {
            buyPrice = getPrice(price);
            buyProvided = true;
        } else if(price.startsWith(SELL_PRICE_PREFIX)) {
            sellPrice = getPrice(price);
            sellProvided = true;
        }

        if((sellPrice <= 0 && sellProvided) || (buyPrice <= 0 && buyProvided)) {
            return new ShopPrice(INCORRECT_PRICE);
        }

        if(sellPrice <= 0 && buyPrice <= 0) {
            return new ShopPrice(NO_PRICE);
        }

        return new ShopPrice(SUCCESS, buyPrice, sellPrice);
    }

    private static int getPrice(String price) {
        try {
            return Integer.parseInt(price.substring(1));
        } catch(NumberFormatException e) {
            return 0;
        }
    }

}
