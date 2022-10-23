import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.openqa.selenium.By;
import com.codeborne.selenide.ex.ElementNotFound;

class LoginPage {
    SelenideElement loginField = $(By.xpath("//*[@id='field_email']"));
    SelenideElement pasField = $(By.xpath("//*[@id=\"field_password\"]"));
    SelenideElement button = $(By.xpath("//*[@class='login-form-actions']/input"));

    public void logIn (String email, String password, String username)
    {

        loginField.setValue(email);
        pasField.setValue(password);
        button.click();



    }
}

class UserPage{
    SelenideElement username = $(By.xpath("//*[@id=\"hook_Block_Navigation\"]/div/div/div[1]/a/div"));
    public void checkData(String expectedUsername, boolean result)
    {
        try{
            assertEquals(expectedUsername, username.text());
        }
        catch (ElementNotFound e)
        {
            assertFalse(result);
        }
    }

}

public class Test_One {
    @Test
    public void loginTest() {
        UserPage userPage = new UserPage();
        LoginPage loginPage = new LoginPage();

        open("https://ok.ru");
        loginPage.logIn("technoPol19", "technoPolis2022", "technoPol19 technoPol19");
        userPage.checkData("technoPol19 technoPol19", true);
        clearBrowserCookies();

        open("https://ok.ru");
        loginPage.logIn("askarxay@yandex.ru", "Paprika", "Аскар Хайбуллин");
        userPage.checkData("Аскар Хайбуллин", false);
        clearBrowserCookies();

    }
}

