package files.componentGroups;

import files.businessComponents.*;
import files.excelUtility.*;
import files.scriptHelper.*;

import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;


public class Repository_Scenarios extends ServerUtils {
    @Test
    public void CreateRepositoryAndListoutReposAndEdit()  {
        // Below Code will Create New Repository
        int value = CommonMethodUtils.generateRandomNumber();
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(value));
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse response = GitRepoUtils.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),response.getName());

        // Below Code will List Repos Present
        OutcomeRepositoryResponse[] listResponse = GitRepoUtils.listRepo();
        System.out.println("Length of Response   :"+listResponse.length);
        for(OutcomeRepositoryResponse repoResponse : listResponse){
            System.out.println("ID   :"+repoResponse.getId());
            System.out.println("Name :"+repoResponse.getName());
        }

        // Below Code will modify Repo which created in first step
        CreateAndModifyRepository modifyPayload = new CreateAndModifyRepository();
        modifyPayload.setName(value+"Modified");
        System.out.println("Repo Need to be Modified with this name:"+modifyPayload.getName());
        OutcomeRepositoryResponse modifyResponse  = GitRepoUtils.modifyRepo(modifyPayload,payload);
        System.out.println("Modified Response Name is :"+modifyResponse.getName());
        Assert.assertEquals("Verified Modified Name is not Present",modifyPayload.getName(),modifyResponse.getName());
    }

    @Test
    public void CreateRepositoryAndDeleteRepository()  {

        // Below Code will create Repo
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(CommonMethodUtils.generateRandomNumber()));
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoUtils.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:"+payload.getName());
        Response deleteResponse = GitRepoUtils.deleteRepo(createResponse);

    }

    @Test(dataProvider = "ReadRepositoryData", dataProviderClass = ExcelUtils.class)
    public void CreateRepositoryAndDeleteRepositoryUsingExcelDataProvider(String NAME, String DESCRIPTION)  {

        System.out.println("Name is        :"+NAME);
        System.out.println("Description is :"+DESCRIPTION);
        // Below Code will create Repo with DataProvider
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(NAME));
        payload.setDescription(DESCRIPTION);
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoUtils.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:"+payload.getName());

        Response deleteResponse = GitRepoUtils.deleteRepo(createResponse);



    }

}
