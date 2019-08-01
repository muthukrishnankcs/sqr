package com.sqrtest;

import generic.utils.DatabaseUtils;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessValidation extends DatabaseUtils {

    @Test(groups={"Employee","Regression"})
    @Parameters({"query","expected"})
    public void salaryLessThan100(@Optional("select count(*) from employee where salary<100;") String query, @Optional("2") int expected) throws SQLException{
        getConnection();
        ResultSet r = getResultSet(query);
        while(r.next()) {
            System.out.println(r.getInt("count"));
            assert(r.getInt("count")==expected);
        }
        closeConnection();
    }

    @Test(groups={"Employee","Regression"})
    @Parameters({"query"})
    public void fetchEIDLessThan1000(@Optional("select eid,name,salary from employee where salary<100;") String query) throws SQLException{
        getConnection();
        ResultSet r = getResultSet(query);
        while(r.next()) {
            System.out.println(r.getInt("eid")+"\t"+r.getString("name")+"\t"+r.getInt("salary"));
        }
        closeConnection();
    }

    @Test(groups={"Employee"})
    @Parameters({"query"})
    public void fetchBirthdayWeekly(@Optional("select eid,name,birthdate from employee where 1 = (FLOOR(DATEDIFF(dd,birthdate,GETDATE()+7) / 365.25))" +
            "-(FLOOR(DATEDIFF(dd,birthdate,GETDATE()) / 365.25));") String query) throws SQLException{
        getConnection();
        ResultSet r = getResultSet(query);
        while(r.next()) {
            System.out.println(r.getInt("eid")+"\t"+r.getString("name")+"\t"+r.getString("birthdate"));
        }
        closeConnection();
    }

    @Test(groups={"Employee"})
    @Parameters({"query"})
    public void fetchEIDExpMoreThan15(@Optional("select eid,name,experience from employee where experience>15;") String query) throws SQLException{
        getConnection();
        ResultSet r = getResultSet(query);
        while(r.next()) {
            System.out.println(r.getInt("eid")+"\t"+r.getString("name")+"\t"+r.getInt("experience"));
        }
        closeConnection();
    }
}
