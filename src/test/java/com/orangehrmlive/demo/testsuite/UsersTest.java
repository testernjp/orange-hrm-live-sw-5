package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class UsersTest extends BaseTest {
    AddUserPage addUserPage;
    AdminPage adminPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUsersPage viewSystemUserPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt()
    {
        //Initialisation
        addUserPage= new AddUserPage();
        adminPage = new AdminPage();
        dashboardPage = new DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUserPage = new ViewSystemUsersPage();
    }
    @Test(groups={"Sanity", "Smoke" , "Regression"})
    public void adminShouldAddUserSuccessFully() throws InterruptedException {
        //Login to Application
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.setClickOnLogin();
        //click On "Admin" Tab
        homePage.clickOnAdminTab();
        //Verify "System Users" Text
        Assert.assertTrue(viewSystemUserPage.setVerifyTextSystemUser().contains("System Users"), "System users not displayed");
        //click On "Add" button
        viewSystemUserPage.setClickingAddButton();
        //Verify "Add User" Text
        Assert.assertTrue(addUserPage.setVerifyAddUserText().contains("Add User"),"Add User is not displayed");
        //Select User Role "Admin"
        //addUserPage.setSelectAdmin();
        //enter Employee Name "Ananya Dash"
        addUserPage.setEnterEmployeeName("Ananya");
        //enter Username
        addUserPage.setEnterTheUserName("Ananya.Dash");
        //Select status "Disable"
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();
        //enter password
        addUserPage.enterPassword1("admin123");
        //enter Confirm Password
        addUserPage.setConfirmPassword("admin123");
        //click On "Save" Button
        addUserPage.setClickOnSaveButton();
        //verify message "Successfully Saved"

    }

    @Test(groups = {"Smoke" ,"Regression" })
    public void searchTheUserCreatedAndVerifyIt() throws InterruptedException {
        //Login to Application
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.setClickOnLogin();
        //	click On "Admin" Tab
        homePage.clickOnAdminTab();
        //	Verify "System Users" Text
        Assert.assertTrue(viewSystemUserPage.setVerifyTextSystemUser().contains("System Users"), "System users not displayed");
        //	Enter Username
        viewSystemUserPage.setEnterUsername("Ananya.Dash");
        //	Select User Role
        addUserPage.setSelectUserRole();
        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();
        //	Click on "Search" Button
        viewSystemUserPage.setEnterSearch();
        //	Verify the User should be in Result list.
        Assert.assertTrue(viewSystemUserPage.setVerifyTheResult().contains("Linda.Anderson"),"Linda.Anderson is not displayed");
    }
    @Test (groups = { "Regression" })
    public void searchTheDeletedUserAndVerifyTheMessageNoRecordFound() {
        //Login to Application
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.setClickOnLogin();
        //	click On "Admin" Tab
        homePage.clickOnAdminTab();
        //	Verify "System Users" Text
        Assert.assertTrue(viewSystemUserPage.setVerifyTextSystemUser().contains("System Users"),"System User is not displayed");
        //	Enter Username
        viewSystemUserPage.setEnterUsername("Ananya.Dash");
        //	Select User Role
        addUserPage.setSelectAdmin();
        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();
        //	Click on "Search" Button
        viewSystemUserPage.setEnterSearch();
        //	verify message "No Records Found"
    }
}
