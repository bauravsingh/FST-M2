package GitHub_RestAssured_Project;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;



public class Restassured{
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    String sshKey;
    int id;
    //final static String Root_URI = "https://api.github.com";

    @BeforeClass
    public void beforeClass(){
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token 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")
                .setBaseUri("https://api.github.com")
                .build();
    }

    @Test(priority = 1)
    public void PostRequest(){
        String reqBody = "{\"title\":\"TestAPIKey\",\"key\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCa4xlRYjHau82AsiekQjGYtsYIeeInPhCp9q4WjgNyTfPBgEDxYJsF9n6uog123F6i2nw1EWn6Rrsv9QuKWZAki9ZwKyqmhq669v/2i6KPKr2U/nXHPHSQ/63XCPuYDda3d/5dJdx816OUzqZSawBUnl4FI3qDT8v4mQqrcQZxbEpcTKmAoDfcyap4oDxMV/PkyTBqPkPoZoWu3GgEf9Yim4YOlnm09xog+YCQPpe3c6FLOkqIo9y6Xvqg9rYiRr3WEVF62an6AIYpNy6rz/r5XDo0OJYtzwaFfOXVD072eKpAuW09N0XifDS5nMXJvcnayoEVHEVm2hhB5nSg7svrP/MhVrOj/3Iyp6VxBjbWldRp8ruenXX+TEEmJ0iQGnD5+S0l8Je/93yh+meFIPwAZzGSHNSNhMUWWyg6AwLwPanAeQQxemgx5yCKk+i9vh4odGMMy0nE9yCH7kVzqmZFWvZXWBvV5U1csdNwX4/UJqDqKscmJoQFWdaGfG5+MME=\"}";
        Response response = given().spec(requestSpec).body(reqBody).when().post("/user/keys");
        id = response.path("id");
        responseSpec = new ResponseSpecBuilder().expectStatusCode(201).build();

    }
    @Test(priority = 2)
    public void getRequest(){
        Response response = given().spec(requestSpec).when().get();
        System.out.println(response.asPrettyString());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
    }
    @Test(priority = 3)
    public void deleteRequest(){
        Response response = given().spec(requestSpec).when().pathParam("keyID",id).delete("/{keyID}");
        System.out.println(response.asPrettyString());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(204).build();
    }
}