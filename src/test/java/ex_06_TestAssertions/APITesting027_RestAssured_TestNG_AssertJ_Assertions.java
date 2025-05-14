package ex_06_TestAssertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class APITesting027_RestAssured_TestNG_AssertJ_Assertions {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingId;

    @Test
    public void test_POST() {
        String payload_POST = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Dutta\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        response = requestSpecification.when().post();

        // Validate response
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Hamcrest assertions for REST Assured validation
        validatableResponse.body("booking.firstname", equalTo("Pramod"));
        validatableResponse.body("booking.lastname", equalTo("Dutta"));
        validatableResponse.body("booking.depositpaid", equalTo(false));
        validatableResponse.body("bookingid", notNullValue());

        // Extract values
        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");

        // Hamcrest assertions
        assertThat(bookingId, is(notNullValue()));
        assertThat(bookingId, greaterThan(0));
        assertThat(firstname, equalTo("Pramod"));
        assertThat(lastname, equalTo("Dutta")); // Fixed from "Brown" to match payload
    }
}