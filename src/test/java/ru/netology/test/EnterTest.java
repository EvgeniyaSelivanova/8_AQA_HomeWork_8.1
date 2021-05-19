package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class EnterTest {

    @BeforeEach
    void clearDataBase() throws SQLException {
        DataHelper.clearAllInAuthCodes();
    }

    @Test
    void shouldCheckTheEnteringInSystem() throws SQLException {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldCheckIncorrectEnteringInSystem() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        int index[] = {1, 2, 3};
        for (int i : index) {
            val AuthInfo = DataHelper.getIncorrectAuthInfo(i);
            loginPage.invalidLogin(AuthInfo, i);
        }
    }

}



