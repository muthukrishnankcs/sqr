package files.testCases;

import com.files.POJOClasses.OrgRepo;
import com.files.businessFunctions.BaseClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;

public class ListOrganisationRepo{
    @BeforeClass
    public void beforeClass()
    {
        BaseClass.setAuthentication();
    }

    public String org = "SprinklrOrg";
    @Test
    public void createOrgRepo() {
        Random randomGenerator = new Random();
        int RepoName = randomGenerator.nextInt(10000);
        OrgRepo repo = new OrgRepo();
        repo.setName(String.valueOf(RepoName));
        System.out.println("New Repo name is :" + repo.getName());
        repo = given()
                .header("content-type", "application/json")
                .body(repo)
                .when()
                .post("/orgs/" + org + "/repos")
                .as(OrgRepo.class);
        System.out.println(repo.toString());

    }
    @Test
    public void listResponse() {
        OrgRepo[] listResponse = given()
                .header("content-type", "application/json")
                .when()
                .get("/orgs/"+org+"/repos")
                .as(OrgRepo[].class);
        System.out.println("Length of Response   :" + listResponse.length);
        for (OrgRepo repoResponse : listResponse) {
            System.out.println("ID   :" + repoResponse.getId());
            System.out.println("Name :" + repoResponse.getName());
        }

    }
}
