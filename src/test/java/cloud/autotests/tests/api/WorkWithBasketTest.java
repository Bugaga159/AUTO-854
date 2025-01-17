package cloud.autotests.tests.api;

import cloud.autotests.tests.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class WorkWithBasketTest extends TestBase {
    @BeforeAll
    static void setProperty() {
        System.setProperty("api", "true");
    }

    @Test
    @Tag("API")
    @DisplayName("Добавить 1 товар в корзину")
    void addOneGoodsToBasket() {
        Map<String,String> cookies = given().contentType(ContentType.JSON)
                .body("[{\"id\":146038407,\"quantity\":1}]")
                .post("/api/composer-api.bx/_action/addToCart")
                .then().assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().cookies();
        given().cookies(cookies)
                .get("/api/composer-api.bx/_action/summary")
                .then().assertThat()
                .statusCode(200)
                .body("", hasSize(1));
    }

    @Test
    @Tag("API")
    @DisplayName("Добавить 2 одинаковых товара в корзину")
    void addTwoGoodsToBasket() {
        Map<String,String> cookies = given().contentType(ContentType.JSON)
                .body("[{\"id\":146038407,\"quantity\":2}]")
                .post("/api/composer-api.bx/_action/addToCart")
                .then().assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().cookies();
        given().cookies(cookies)
                .get("/api/composer-api.bx/_action/summary")
                .then().assertThat()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].quantity", is(2));
    }
}
