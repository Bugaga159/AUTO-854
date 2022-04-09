package cloud.autotests.tests.ui;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AddGoodsToBasket extends TestBase {

    @Test
    @Tag("UI")
    @Description("Проверка на добавление товара в корзину и количество товара в корзине равное 1")
    @DisplayName("Добавить товар в пустую корзину")
    void addGoodsToEmptyBasket() {
        step("Open 'https://www.ozon.ru/'", () -> {
            open("/");
        });
        step("В поле поиска ввести 'Футболка' и нажать Enter", () -> {
            $("input[name='text']").setValue("Футболка").pressEnter();
        });
        step("Кликнуть на 'В корзину' у первого товара", () -> {
            $(byText("В корзину")).click();
        });
        step("Количество в корзине '1' в меню", () -> {
            $("[data-widget='headerIcon']>span.tsCaptionBold").shouldHave(text("1"));
        });
        step("Кликнуть на 'Корзина'", () -> {
            $(byText("Корзина")).click();
        });
        step("Закрыть alertPopup", () -> {
            $("[data-widget='alertPopup'] .z4b button").click();
        });
        step("Количество в корзине '1'", () -> {
            $$("div.qa2").shouldHave(CollectionCondition.size(1));
        });
    }

    @Test
    @Tag("UI")
    @Description("Проверка на добавление товара в корзину и количество товара в корзине равное 2")
    @DisplayName("Добавить товар в не пустую корзину")
    void addGoodsToFullBasket() {
        step("Open 'https://www.ozon.ru/'", () -> {
            open("/");
        });
        step("Добавление товара через api", () -> {
            Map<String,String> res = given().contentType(ContentType.JSON)
                    .body("[{\"id\":525527011,\"quantity\":1}]")
                    .post("/api/composer-api.bx/_action/addToCart")
                    .then().assertThat().statusCode(200).extract().cookies();
            res.forEach((k,y) -> getWebDriver().manage().addCookie(new Cookie(k,y)));
        });
        step("Refresh page", () -> {
            refresh();
        });
        step("Количество в корзине '1' в меню", () -> {
            $("[data-widget='headerIcon']>span.tsCaptionBold").shouldHave(text("1"));
        });
        step("В поле поиска ввести 'Футболка' и нажать Enter", () -> {
            $("input[name='text']").setValue("Футболка").pressEnter();
        });
        step("Кликнуть на 'В корзину' у первого товара", () -> {
            $(byText("В корзину")).click();
        });
        step("Количество в корзине '2' в меню", () -> {
            $("[data-widget='headerIcon']>span.tsCaptionBold").shouldHave(text("2"));
        });
        step("Кликнуть на 'Корзина'", () -> {
            $(byText("Корзина")).click();
        });
        step("Закрыть alertPopup", () -> {
            $("[data-widget='alertPopup'] .z4b button").click();
        });
        step("Количество в корзине '2'", () -> {
            $$("div.qa2").shouldHave(CollectionCondition.size(2));
        });
    }
}
