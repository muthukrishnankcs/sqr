package files.testCases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.files.POJOClasses.InitializeVariables;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class ListUsersRepo {
   private List<InitializeVariables> initializeVariables;

    @Test
    public void StatusValidation() throws Exception{
        RestAssured
            .given()
                .auth()
                .preemptive()
                .basic("PramatiImaginea","Sprinklr123")
            .when()
                .get("https://api.github.com/user/repos")
            .then()
                .statusCode(200);
    }

    public Response ExtractResponse() throws Exception{
        Response Response = RestAssured
                                .given()
                                    .auth()
                                    .preemptive()
                                    .basic("PramatiImaginea", "Sprinklr123")
                                .when()
                                    .get("https://api.github.com/user/repos");
        return Response;
    }

    @Test
    public void ValidateResponse() throws Exception {
        String JsonResponse = ExtractResponse().asString();
        System.out.println("Response: " + JsonResponse);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.writeValue(new File("github.json"), ExtractResponse().as(InitializeVariables[].class) );
        InitializeVariables[] initializeVariables = mapper.readValue(new File("github.json"), InitializeVariables[].class);
        System.out.println(initializeVariables.length);
        InitializeVariables assertVariable = null;
        for(InitializeVariables initializeVariable : initializeVariables) {
            if(initializeVariable.getName().matches("abcHello-World")){
                assertVariable = initializeVariable;
            }
        }
        Assert.assertEquals(assertVariable.getFull_name(), "pramatiimaginea/abcHello-World");
        Assert.assertEquals(assertVariable.getOwner().getLogin(), "pramatiimaginea");
        Assert.assertEquals(assertVariable.getOwner().getUrl(), "https://api.github.com/users/pramatiimaginea");
        Assert.assertEquals(assertVariable.getOwner().getReposUrl(), "https://api.github.com/users/pramatiimaginea/repos");
    }
}
