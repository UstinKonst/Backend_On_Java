package Less4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MealPlanTest extends AbstractTest {

    Request request = new Request();

    @Test
    void MPTest() {

////      Получить Имя и Hash-Код
//        given()
//                .body("{\n"
//                        + " \"username\": \"konstantin\" "
//                        + "}")
//                .when()
//                .post("https://api.spoonacular.com/users/connect")
//                .prettyPeek();
//
////        Создать список покупок
//        given().spec(requestSpecificationMP)
//                .when()
//                .post("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/2023-01-01/2023-01-30")
//                .prettyPeek();


//        Добавить товар с список
        String id = given().spec(requestSpecificationMP)
                .body(request)
                .when()
                .post("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/items")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();



//        Посмотреть список
        Responce res1 = given().spec(requestSpecificationMP)
                .get("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Responce.class);
        assertThat(res1.getCost(), equalTo(0.71));




//        Удалить товар из списка
        Responce res2 = given().spec(requestSpecificationMP)
                .delete("https://api.spoonacular.com/mealplanner/konstantin0/shopping-list/items/" + id )
                .then()
                .extract()
                .response()
                .body()
                .as(Responce.class);
        assertThat(res2.getStatus(), containsString("success"));
    }
}
