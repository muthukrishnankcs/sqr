package com.sqrtest;

import generic.utils.TestUtil;
import generic.utils.Xls_Reader;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.python.util.PythonObjectInputStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class ValidateSqrCommands {

    Xls_Reader sqr = new Xls_Reader(new File(getClass().getClassLoader().getResource("dataset.xlsx").getFile()).getAbsolutePath());
    /*@Test(dataProvider="getTestData")
    public void validateCommands(String Command, String Input)
    {
        String ip = Command+" "+Input;
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:/Users/ramyaraju/Workspace/Master/sqr/src/main/java/com/sqrtest/lineparser.py");
    // execute a function that takes a string and returns a string
        PyObject someFunc = interpreter.get("parse_move");
        PyObject result = someFunc.__call__(new PyString(ip));
        System.out.println(result);
        String realResult = (String) result.__tojava__(String.class);
        System.out.println(realResult);
    }*/

    @Test(dataProvider="getTestDataVariable")
    public void validateMethodWithInput(String Method, String Input, String Output)
    {
        System.out.println(Input);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("C:/Users/ramyaraju/Workspace/Master/sqr/src/main/java/com/sqrtest/lineparser.py");
        PyObject someFunc = interpreter.get(Method);
        PyObject result = someFunc.__call__(new PyString(Input));
        System.out.println(result);
//        assert(result.equals(Output));
    }


    @DataProvider
    public Object[][] getTestData() {

        return TestUtil.getData(sqr, this.getClass().getSimpleName());

    }

    @DataProvider
    public Object[][] getTestDataVariable() {

        return TestUtil.getData(sqr, "ValidateMethod");

    }

}
