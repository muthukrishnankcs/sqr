package files.testCases;
import static io.restassured.RestAssured.given;
import files.businessFunctions.*;
import files.excelUtility.*;
import files.POJOClasses.*;
import org.hamcrest.Matchers;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class RepositoryScenarios {
    @BeforeClass
    public void beforeClass()
    {
        BaseClass.setAuthentication();
    }



    @Test
    public void createRepositoryAndListoutReposAndEdit()  {
        // Below Code will Create New Repository
        int value = CommonMethods.generateRandomNumber();
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(value));
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse response = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),response.getName());

        // Below Code will List Repos Present
        OutcomeRepositoryResponse[] listResponse = GitRepoMethods.listRepo();
        System.out.println("Length of Response   :"+listResponse.length);
        for(OutcomeRepositoryResponse repoResponse : listResponse){
            System.out.println("ID   :"+repoResponse.getId());
            System.out.println("Name :"+repoResponse.getName());
        }

        // Below Code will modify Repo which created in first step
        CreateAndModifyRepository modifyPayload = new CreateAndModifyRepository();
        modifyPayload.setName(value+"Modified");
        System.out.println("Repo Need to be Modified with this name:"+modifyPayload.getName());
        OutcomeRepositoryResponse modifyResponse  = GitRepoMethods.modifyRepo(modifyPayload,payload);
        System.out.println("Modified Response Name is :"+modifyResponse.getName());
        Assert.assertEquals("Verified Modified Name is not Present",modifyPayload.getName(),modifyResponse.getName());
    }

    @Test
    public void createRepositoryAndDeleteRepository()  {


        // Below Code will create Repo
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(CommonMethods.generateRandomNumber()));
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not SameKKK",payload.getName(),createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:"+payload.getName());
        Response deleteResponse = GitRepoMethods.deleteRepo(createResponse);

    }


    @Test(dataProvider = "readRepositoryData", dataProviderClass = ExcelUtils.class)
    public void createRepositoryAndDeleteRepositoryUsingExcelDataProvider(String NAME, String DESCRIPTION)  {

        System.out.println("Name is        :"+NAME);
        System.out.println("Description is :"+DESCRIPTION);
        // Below Code will create Repo with DataProvider
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(NAME));
        payload.setDescription(DESCRIPTION);
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:"+payload.getName());

        Response deleteResponse = GitRepoMethods.deleteRepo(createResponse);



    }

    @Test
    @Parameters("repo")
    public void createRepositoryAndDeleteRepositoryUsingParameter(String repo)  {

        // Below Code will create Repo
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(repo);
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:"+payload.getName());
        Response deleteResponse = GitRepoMethods.deleteRepo(createResponse);

    }

    @Test
    // get languages api returns an empty list
    public void getLanguage() {

        given().when().get("/repos/pramatiimaginea/abcHello-World/languages")
                .then().statusCode(200).and().body("isEmpty()", Matchers.is(true));

    }

}
