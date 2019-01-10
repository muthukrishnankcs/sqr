package files.testCases;

import com.files.POJOClasses.CreateAndModifyRepository;
import com.files.POJOClasses.OutcomeRepositoryResponse;
import com.files.businessFunctions.BaseClass;
import com.files.businessFunctions.GitRepoMethods;
import com.files.excelUtility.ExcelUtils;

import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


public class RepositoryScenariosUsingFactoryMethod extends BaseClass {

    //   PropertyClass propertyClass=new PropertyClass();
    private  String NAME,DESCRIPTION;
    @Factory(dataProvider = "readRepositoryData", dataProviderClass = ExcelUtils.class)
    public RepositoryScenariosUsingFactoryMethod(String NAME, String DESCRIPTION){
        this.NAME=NAME;
        this.DESCRIPTION=DESCRIPTION;
    }


    @Test
    public void createRepositoryAndDeleteRepositoryUsingFactoryMethod() {

        System.out.println("Name is        :" + NAME);
        System.out.println("Description is :" + DESCRIPTION);
        // Below Code will create Repo with DataProvider
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(NAME));
        payload.setDescription(DESCRIPTION);
        System.out.println("Repo Need to be Created:" + payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same", payload.getName(), createResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:" + payload.getName());

        Response deleteResponse = GitRepoMethods.deleteRepo(createResponse);
    }
    @Test
    public void createRepositoryAndEditRepositoryAndDeleteRepository()  {
        // Below Code will Create New Repository
        System.out.println("Name is        :" + NAME);
        System.out.println("Description is :" + DESCRIPTION);
        CreateAndModifyRepository payload = new CreateAndModifyRepository();
        payload.setName(String.valueOf(NAME));
        payload.setDescription(DESCRIPTION);
//        payload.setName(String.valueOf(value));
        System.out.println("Repo Need to be Created:"+payload.getName());
        OutcomeRepositoryResponse createResponse = GitRepoMethods.createRepo(payload);
        Assert.assertEquals("Expected Name and Actual Name is Not Same",payload.getName(),createResponse.getName());

        // Below Code will modify Repo which created in first step
        CreateAndModifyRepository modifyPayload = new CreateAndModifyRepository();
        modifyPayload.setName(NAME+"Modified");
        System.out.println("Repo Need to be Modified with this name:"+modifyPayload.getName());
        OutcomeRepositoryResponse modifyResponse  = GitRepoMethods.modifyRepo(modifyPayload,payload);
        System.out.println("Modified Response Name is :"+modifyResponse.getName());
        Assert.assertEquals("Verified Modified Name is not Present",modifyPayload.getName(),modifyResponse.getName());

        // Below Code will Delete Repo which created in first step
        System.out.println("Repo Need to be Deleted:" + modifyResponse.getName());

        Response deleteResponse = GitRepoMethods.deleteRepo(modifyResponse);

    }




}
