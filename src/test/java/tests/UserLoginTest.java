package tests;

import framework.base.BaseTest;
import framework.pages.LoginPage;
import framework.utils.JsonReader;
import framework.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class UserLoginTest extends BaseTest {

    @DataProvider(name = "jsonUsers")
    public Object[][] getJsonData() throws IOException {
        String path = "src/test/resources/testdata/users.json";
        List<UserData> users = JsonReader.readUsers(path);

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }

    @Test(dataProvider = "jsonUsers")
    public void testLoginWithJson(UserData user) {
        LoginPage loginPage = new LoginPage(getDriver());
        if (user.isExpectSuccess()) {
            Assert.assertTrue(loginPage.login(user.getUsername(), user.getPassword()).isLoaded(),
                              "Login failed for: " + user.getDescription());
        } else {
            loginPage.loginExpectingFailure(user.getUsername(), user.getPassword());
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                              "Error not displayed for: " + user.getDescription());
        }
    }
}
