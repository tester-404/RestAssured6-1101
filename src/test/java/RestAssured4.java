import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssured4 {
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


}
