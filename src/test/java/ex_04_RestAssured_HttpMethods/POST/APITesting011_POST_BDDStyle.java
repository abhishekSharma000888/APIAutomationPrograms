package ex_04_RestAssured_HttpMethods.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITesting011_POST_BDDStyle {

    @Test
    public void test_POST_Auth() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .when()
                .log().all()
                .post()
                .then().log().all()
                .statusCode(200);


    }
}
