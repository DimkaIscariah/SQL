package ru.netology.Test;

import ru.netology.Data.DataHelper;
import ru.netology.Data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.Page.DashboardPage;
import ru.netology.Page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.Data.SQLHelper.cleanDatabase;

public class SQLTest {
    DashboardPage dashboardPage;

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    void shouldSuccessfullyEnter() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }
}