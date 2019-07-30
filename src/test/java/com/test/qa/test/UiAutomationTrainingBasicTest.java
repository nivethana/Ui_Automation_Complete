package com.test.qa.test;

import com.test.qa.pageobjects.utils.PageBase;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.qa.pageobjects.pages.ABTestPage;
import com.test.qa.pageobjects.pages.CheckBoxPage;
import com.test.qa.pageobjects.pages.DropDownPage;
import com.test.qa.pageobjects.pages.ForgotPasswordPage;
import com.test.qa.pageobjects.pages.HomePage;
import com.test.qa.pageobjects.pages.LoginPage;
import com.test.qa.pageobjects.pages.LoginSecurePage;
import com.test.qa.pageobjects.utils.Constants;
import com.test.qa.testdata.UserDetailsDataProvider;
import com.test.qa.utils.TestBase;

/**
 * UiAutomationTrainingBasicTest.java - class to execute Tests Created by
 * SrirankanK on 10/3/2018.
 */
public class UiAutomationTrainingBasicTest extends TestBase {

	/**
	 * Verify Home Page Displayed
	 */
	@Test(groups = "REGRESSION", priority = 1)
	public void testVerifyHomePage() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.AB_TEST_LINK);
		ABTestPage.waitTillHeaderLoad();
		softAssert.assertTrue(ABTestPage.isABTestPageDisplayed(), "Home Page is not Displayed");
		ABTestPage.navigateBack();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		softAssert.assertAll();
	}

	/**
	 * Verify Home Page Displayed Slow
	 */
	@Test(groups = "REGRESSION", priority = 2)
	public void testVerifyHomePage2() {
		softAssert = new SoftAssert();
		//PageBase.staticWait(2);
      //  softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
//        PageBase.staticWait(2);
//        HomePage.clickLink(Constants.ABTestLink);
//        PageBase.staticWait(2);
//        ABTestPage.waitTillHeaderLoad();
//        PageBase.staticWait(2);
//        softAssert.assertTrue(ABTestPage.isABTestPageDisplayed(), "Home Page is not Displayed");
//        PageBase.staticWait(2);
//        ABTestPage.navigateBack();
//        PageBase.staticWait(2);
//        softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
//        PageBase.staticWait(2);
		softAssert.assertAll();
	}

	/**
	 * Verify Check Box
	 */
	@Test(groups = "REGRESSION", priority = 3)
	public void testVerifyCheckBox() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.CHECK_BOX_LINK);
		softAssert.assertTrue(CheckBoxPage.isCheckBox1Displayed(), "CheckBox1 is not Displayed");
		softAssert.assertTrue(CheckBoxPage.isCheckBox2Displayed(), "CheckBox2 is not Displayed");
		CheckBoxPage.checkCheckBox1();
		softAssert.assertTrue(CheckBoxPage.isCheckBox1Checked(), "CheckBox1 is not Checked");
		CheckBoxPage.unCheckCheckBox1();
		softAssert.assertFalse(CheckBoxPage.isCheckBox1Checked(), "CheckBox1 is Checked");
		CheckBoxPage.checkCheckBox2();
		softAssert.assertTrue(CheckBoxPage.isCheckBox2Checked(), "CheckBox2 is not Checked");
		CheckBoxPage.unCheckCheckBox2();
		softAssert.assertFalse(CheckBoxPage.isCheckBox2Checked(), "CheckBox2 is Checked");
		CheckBoxPage.navigateBack();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		softAssert.assertAll();
	}

	/**
	 * Verify Drop Down
	 */
	@Test(groups = "REGRESSION", priority = 4)
	public void testVerifyDropDown() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.DROP_DOWN_LINK);
		softAssert.assertTrue(DropDownPage.isDropDownDisplayed(), "DropDown is not Displayed");
		DropDownPage.setDropDownOption(Constants.OPTION_1);
		softAssert.assertEquals(DropDownPage.getSelectedOption(), Constants.OPTION_1, "DropDown value is incorrect");
		DropDownPage.setDropDownOption(Constants.OPTION_2);
		softAssert.assertEquals(DropDownPage.getSelectedOption(), Constants.OPTION_2, "DropDown value is incorrect");
		softAssert.assertAll();
	}

	/**
	 * Verify Forgot Password
	 */
	@Test(groups = "REGRESSION", priority = 5)
	public void testVerifyForgotPassword() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.FORGOT_PASSWORD_LINK);
		ForgotPasswordPage.setEmail(Constants.SAMPLE_MAIL);
		ForgotPasswordPage.clickSubmit();
		softAssert.assertEquals(ForgotPasswordPage.getResult(), Constants.EMAIL_RESULT,
				"Email Submit Result is incorrect");
		softAssert.assertAll();
	}

	/**
	 * Verify Login Valid Scenario
	 */
	@Test(groups = "REGRESSION", priority = 6)
	public void testVerifyLogin() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.LOGIN_LINK);
		LoginPage.setUserName(Constants.LOGIN_USER_NAME);
		LoginPage.setPassword(Constants.LOGIN_PASSWORD);
		LoginPage.clickSubmit();
		softAssert.assertTrue(LoginSecurePage.isLoginSecurePageDisplayed(), "Login Secure Page is not Displayed");
		softAssert.assertTrue(LoginSecurePage.isLoginAlertDisplayed(), "Login Secure Page Alert is not Displayed");
		softAssert.assertTrue(LoginSecurePage.getLoginAlertMessage().contains(Constants.LOGIN_SUCCESS_MSG),
				"Invlaid Message");
		LoginSecurePage.clickLogout();
		softAssert.assertTrue(LoginPage.isLoginPageDisplayed(), "Login Secure Page is not Displayed");
		softAssert.assertTrue(LoginPage.isLogoutAlertDisplayed(), "Login Secure Page Alert is not Displayed");
		softAssert.assertTrue(LoginPage.getLogoutAlertMessage().contains(Constants.LOGOUT_SUCCESS_MSG),
				"Invlaid Message");
		softAssert.assertAll();
	}

	/**
	 * Verify Login Invalid Scenario
	 */
	@Test(groups = "REGRESSION", priority = 7, dataProvider = "MultipleUserDetails", dataProviderClass = UserDetailsDataProvider.class)
	public void testVerifyLogin(String username, String password) {
		softAssert = new SoftAssert();
		PageBase.staticWait(2);
		softAssert.assertTrue(HomePage.isHomePageDisplayed(), "Home Page is not Displayed");
		HomePage.clickLink(Constants.LOGIN_LINK);
		LoginPage.setUserName(username);
		LoginPage.setPassword(password);
		LoginPage.clickSubmit();
		softAssert.assertTrue(LoginPage.isLoginPageDisplayed(), "Login Page is not Displayed");
		softAssert.assertTrue(LoginPage.isLogoutAlertDisplayed(), "Login Page Alert is not Displayed");
		softAssert.assertTrue(LoginPage.getLogoutAlertMessage().contains(Constants.LOGIN_INVALID_MSG),
				"Invlaid Message");
		softAssert.assertAll();
	}
}
