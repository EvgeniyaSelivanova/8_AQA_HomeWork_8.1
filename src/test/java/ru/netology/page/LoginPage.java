package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    @FindBy(css = "[data-test-id=login] input")
    private SelenideElement loginField;
    @FindBy(css = "[data-test-id=password] input")
    private SelenideElement passwordField;
    @FindBy(css = "[data-test-id=action-login]")
    private SelenideElement loginButton;

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }

    public void invalidLogin(DataHelper.AuthInfo info, int index) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        if (index == 3) {
            errorInformThreeIncorrectPasswords();
        } else {
            errorInform();
            loginField.sendKeys(Keys.CONTROL + "A");
            loginField.sendKeys(Keys.BACK_SPACE);
            passwordField.sendKeys(Keys.CONTROL + "A");
            passwordField.sendKeys(Keys.BACK_SPACE);
        }
    }

    public LoginPage errorInform() {
        $("[data-test-id='error-notification'] .notification__content").shouldBe(visible);
        return page(LoginPage.class);
    }

    public BlockedPage errorInformThreeIncorrectPasswords() {
        return page(BlockedPage.class);
    }
}
