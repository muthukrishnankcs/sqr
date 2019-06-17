package com.sqrtest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ValidateBankDetails {

    @Test(groups = {"BankDetails", "Regression"})
    @Parameters({"EID","Name","AccountNo"})
    public void validateBankDetails(String EID, String Name, String AccountNo){
        if(Name == null) assert(AccountNo == null);
    }
}
    /*1. Run single class file - mvn -Dtest=BusinessValidation test
    2. Run single test method from class - mvn -Dtest=BusinessValidation#validateSqlCountToZero test
    3. Run groups - mvn test -Dgroups=Employee -DcountQuery="select count(*) from employee where eid<100;"
    4. Run testng suite - mvn clean test -Dsurefire.suiteXmlFiles=SampleTestNGSuite.xml
    5. Run multiple test class file - mvn -Dtest=BusinessValidation,BankDetails test
    6. Run multiple methods - mvn -Dtest=BusinessValidation#validateSqlCountToZero+validateCount test

    mvn clean test -Dsurefire.suiteXmlFiles=SampleTestNGSuite.xml --log-file log.txt*/