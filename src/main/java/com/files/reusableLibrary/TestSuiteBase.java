package com.files.reusableLibrary;

import com.files.utilities.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

public class TestSuiteBase extends TestBase {


    @BeforeSuite
    public void checkSuiteSkip() throws Exception {

        Initialize();
        APP_LOGS.debug("Checking Runmode of 'Sprint_1' :");
        if(! TestUtil.isSuiteRunnable(suiteXls, "Sprint_1")){

            APP_LOGS.debug("RunMode of 'Sprint_1' is set to 'No' So Skipped :");
            throw new SkipException("RunMode of 'Sprint_1' is set to 'No' So Skipped :");
        }
    }

}
