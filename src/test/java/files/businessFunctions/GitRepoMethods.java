package files.businessFunctions;

import io.restassured.response.Response;
import files.POJOClasses.*;


import static io.restassured.RestAssured.given;



public class GitRepoMethods {
    static final String createlistRepoUrl = "/user/repos";
    static final String modifydeleteRepoUrl = "/repos/pramatiimaginea/";

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
                .post(createlistRepoUrl)
                .as(OutcomeRepositoryResponse.class);
        return response;

    }

    public static OutcomeRepositoryResponse[] listRepo()
    {
        OutcomeRepositoryResponse[] listResponse = given()
                .header("content-type", "application/json")
                .when()
                .get(createlistRepoUrl)
                .as(OutcomeRepositoryResponse[].class);
        return listResponse;
    }

    public static OutcomeRepositoryResponse modifyRepo(CreateAndModifyRepository modifyPayload,CreateAndModifyRepository payload)
    {
        OutcomeRepositoryResponse modifyResponse = given()
                .header("content-type", "application/json")
                .body(modifyPayload)
                .when()
                .patch(modifydeleteRepoUrl+ payload.getName())
                .as(OutcomeRepositoryResponse.class);
        return modifyResponse;
    }

    public static Response deleteRepo(OutcomeRepositoryResponse response) {
        Response deleteResponse = given()
                .when()
                .delete(modifydeleteRepoUrl + response.getName())
                .then()
                .statusCode(204)
                .extract().response();
        return deleteResponse;
    }

}


