package Less4;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RestAPITest extends AbstractTest {

    @Test
    void getTest() {
//        get_1
        JsonPath get_1 = given().spec(requestSpecification)
                .queryParam("cuisine", "Japanese")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(get_1.get("totalResults"), equalTo(30));

//        get_2
        JsonPath get_2 = given().spec(requestSpecification)
                .queryParam("equipment", "pan")
                .queryParam("includeIngredients", "tomato")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(get_2.get("results[0].id"), equalTo(794349));


//        get_3
        JsonPath get_3 = given().spec(requestSpecification)
                .queryParam("limitLicense", "true")
                .queryParam("minFat", "40")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(get_3.get("results[0].nutrition.nutrients[0].name"), equalTo("Fat"));


//        get_4
        JsonPath get_4 = given().spec(requestSpecification)
                .queryParam("cuisine", "African")
                .queryParam("includeIngredients", "meat")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(get_4.get("results[0].imageType"), equalTo("jpg"));

//        get_5
        JsonPath get_5 = given().spec(requestSpecification)
                .queryParam("cuisine", "Mexican")
                .queryParam("minPhosphorus", "100")
                .queryParam("equipment", "bowl")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(get_5.get("offset"), equalTo(0));

    }

    @Test
    void postTest() {
//        post_1
        given().spec(requestSpecification)
                .formParam("ingredientList","cucumber")
                .when()
                .post(getBaseUrl()+"recipes/cuisine");
//        post_2
        given().spec(requestSpecification)
                .formParam("ingredientList","tomato")
                .when()
                .post(getBaseUrl()+"recipes/cuisine");
//        post_3
        given().spec(requestSpecification)
                .formParam("title","Soba Noodle In Kombu Dashi With Teriyaki Salmon")
                .when()
                .post(getBaseUrl()+"recipes/cuisine");
//        post_4
        given().spec(requestSpecification)
                .formParam("ingredientList","banana")
                .formParam("title","Berry Banana Breakfast Smoothie")
                .when()
                .post(getBaseUrl()+"recipes/cuisine");

//        post_5
        given().spec(requestSpecification)
                .formParam("ingredientList","rice")
                .formParam("title","Japanese Sushi")
                .when()
                .post(getBaseUrl()+"recipes/cuisine");
    }
}
