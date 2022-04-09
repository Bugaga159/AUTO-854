package cloud.autotests.tests.ui;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CheckOnTravelPageTest {

    @Test
    @Tag("UI")
    @DisplayName("По умолчанию на странице Travel выбран раздел Авиабилеты")
    void checkTitleInTravelPageTest() {
        step("Open url 'https://www.ozon.ru/travel/'", () ->
                open("/travel/"));

        step("По умолчанию выбран раздел 'Авиабилеты'", () -> {
            $(byText("Поиск дешёвых авиабилетов")).shouldBe(Condition.visible);
        });
    }

}
