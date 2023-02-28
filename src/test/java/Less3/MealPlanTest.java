package Less3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MealPlanTest extends AbstractTest{
    @Test
    void MPTest() {

////      Получить Имя и Hash-Код
//        given()
//                .queryParam("apiKey", getApiKey())
//                .body("{\n"
//                        + " \"username\": \"konstantin\" "
//                        + "}")
//                .when()
//                .post("https://api.spoonacular.com/users/connect")
//                .prettyPeek();
//
////        Создать список покупок
//        given()
//                .queryParam("hash", "de89790bc1f3822ca92de68628560033b0d2347f")
//                .queryParam("apiKey", getApiKey())
//                .when()
//                .post("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/2023-01-01/2023-01-30")
//                .prettyPeek();


//        Добавить товар с список
        String id = given()
                .queryParam("hash", "de89790bc1f3822ca92de68628560033b0d2347f")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

//        Посмотреть список
        given()
                .queryParam("hash", "de89790bc1f3822ca92de68628560033b0d2347f")
                .queryParam("apiKey", getApiKey())
                .get("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list")
                .prettyPeek();

//        Удалить товар из списка
        given()
                .queryParam("hash", "de89790bc1f3822ca92de68628560033b0d2347f")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/items/" + id)
                .then()
                .statusCode(200);

    }
}
