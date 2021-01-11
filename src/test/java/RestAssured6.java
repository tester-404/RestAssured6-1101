import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssured6 {
    @Test
    public void Test01() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200);
    }

    @Test
    public void Test02(){
        RestAssured.
                baseURI="https://reqres.in/api";
        RestAssured.
                given().
                get("/user?page=2").
                then().
                statusCode(200).
                body("data[1].id",equalTo(8))
                .log().all();
        }

    @Test
    public void getTest() {
        RestAssured.
        baseURI = "https://reqres.in/api";

        RestAssured.
                given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItems("George","Rachel"));
             //   log().all();

    }

    @Test
    public void postTest() {
        Map<String, Object> map = new HashMap<String, Object>();
        //   map.put("\"name\"", "Raghav");
//        map.put("name", "Raghav");
//        map.put("job", "Teacher");
//        System.out.println(map);

        //output of information in the JSON
        JSONObject request = new JSONObject(map);
        request.put("name", "Raghav");
        request.put("job", "Teacher");
//        System.out.println();
        System.out.println(request);
        System.out.println(request.toJSONString());

        RestAssured.
                baseURI = "https://reqres.in/api";

        RestAssured.
                given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201)
                .log().all();


    }


}
