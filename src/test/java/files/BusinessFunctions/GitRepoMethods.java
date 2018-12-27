package files.BusinessFunctions;

import io.restassured.response.Response;
import files.POJOClasses.*;


import static io.restassured.RestAssured.given;

public class GitRepoMethods {
    public GitRepoMethods()
    {
        // CPOnsuturetir
    }

    public static OutcomeRepositoryResponse createRepo(CreateAndModifyRepository payload)
    {
        OutcomeRepositoryResponse response  = given()
                .header("content-type", "application/json")
                .body(payload)
                .when()
                .post("/user/repos")
                .as(OutcomeRepositoryResponse.class);
        return response;

    }

    public static OutcomeRepositoryResponse[] listRepo()
    {
        OutcomeRepositoryResponse[] listResponse = given()
                .header("content-type", "application/json")
                .when()
                .get("/user/repos")
                .as(OutcomeRepositoryResponse[].class);
        return listResponse;
    }

    public static OutcomeRepositoryResponse modifyRepo(CreateAndModifyRepository modifyPayload,CreateAndModifyRepository payload)
    {
        OutcomeRepositoryResponse modifyResponse = given()
                .header("content-type", "application/json")
                .body(modifyPayload)
                .when()
                .patch("/repos/pramatiimaginea/" + payload.getName())
                .as(OutcomeRepositoryResponse.class);
        return modifyResponse;
    }

    public static Response deleteRepo(OutcomeRepositoryResponse response) {
        Response deleteResponse = given()
                .when()
                .delete("repos/pramatiimaginea/" + response.getName())
                .then()
                .statusCode(204)
                .extract().response();
        return deleteResponse;
    }

}
