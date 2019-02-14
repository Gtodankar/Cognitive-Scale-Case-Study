import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class GetApiTest {

    @Test
    public void testResponsebody() {
        Response response = get("https://api.github.com/users/octocat");
        response.
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                time(lessThan(3000L));

        System.out.println("Response data is :" + response.asString());
        System.out.println("Response Time in ms :" + response.getTime());


        Assert.assertEquals(response.getBody().asString().toLowerCase().contains("location"), true);
        System.out.println("location present" );

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        System.out.println("User ID received from Response : " + id);
        Assert.assertEquals(id, 583231);

        String location=jsonPath.get("location");
        Assert.assertEquals(location, "San Francisco");
        System.out.println("User location received from Response : " + location);
    }


}
