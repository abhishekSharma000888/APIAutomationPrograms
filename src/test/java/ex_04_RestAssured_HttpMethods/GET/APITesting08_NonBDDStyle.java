package ex_04_RestAssured_HttpMethods.GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting08_NonBDDStyle {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    String pincode;

    @Test
    public void test_GET_NonBDD(){

        String pincode =  "560066";
        // GIVEN
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // WHEN
        response = r.when().log().all().get();

        System.out.println(response.asString());

        // THEN
        vr = response.then().log().all();
        vr.statusCode(200);


    }

    @Test
    public void test_GET_NonBDD_Neagtive(){

        String pincode =  "@";
        // GIVEN
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // WHEN
        response = r.when().log().all().get();

        System.out.println(response.asString());

        // THEN
        vr = response.then().log().all();
        vr.statusCode(404);


    }

}
