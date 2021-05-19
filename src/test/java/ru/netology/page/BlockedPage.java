package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BlockedPage {
    private SelenideElement blockedNotificationPage = $("[data-test-id=error_notification]");

    public BlockedPage() {
        blockedNotificationPage.shouldBe(Condition.visible);
    }
}
