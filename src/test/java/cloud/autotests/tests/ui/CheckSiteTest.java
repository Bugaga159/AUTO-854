package cloud.autotests.tests.ui;

import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.tests.TestBase;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckSiteTest extends TestBase {

    @Test
    @Tag("UI")
    @Description("Проверка title сайта на содержание текста " +
            "'OZON — интернет-магазин. Миллионы товаров по выгодным ценам'")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://www.ozon.ru/'", () ->
                open("/"));
        step("Page title should have text 'OZON — интернет-магазин. Миллионы товаров по выгодным ценам'", () -> {
            String expectedTitle = "OZON — интернет-магазин. Миллионы товаров по выгодным ценам";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("UI")
    @Description("Проверка на отсутствие ошибок в консоли сайта")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://www.ozon.ru/'", () ->
                open("/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @Tag("UI")
    @Description("Проверка title сайта в разделе Travel")
    @DisplayName("Page Travel title should have header text")
    void checkTitleInTravelPageTest() {
        step("Open url 'https://www.ozon.ru/travel/'", () ->
                open("/travel/"));

        step("Page title should contains text 'Ozon Travel'", () -> {
            String expectedTitle = "Ozon Travel";
            String actualTitle = title();

            assertThat(actualTitle).contains(expectedTitle);
        });
    }
}
