package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class PageObject {

    private SelenideElement form;


    PageObject() {
        open("http://localhost:9999");
        form = $("[action]");
    }

    public void setCity(String city) {
        form.$(cssSelector("[data-test-id=city] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=city] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=city] input")).sendKeys(city);
    }

    public void setDate(String date) {
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        form.$(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        form.$(cssSelector("[data-test-id=date] input")).sendKeys(date);
    }

    public void setFullName(String name) {
        form.$(cssSelector("[name=name]")).sendKeys(name);
    }

    public void setPhone(String phone) {
        form.$(cssSelector("[name=phone]")).sendKeys(phone);
    }

    public void agreeWithConditions() {
        form.$(cssSelector("[data-test-id=agreement]")).click();
    }
    public void apply() {
        form.$(byText("Запланировать")).click();
    }

    public void verifyPopup(String text) {
        $(withText(text)).waitUntil(Condition.visible, 15000);
    }

    public void verifyWarning(String text) {
        $(byText(text)).waitUntil(Condition.visible, 15000);
    }


    public void confirm() {
        $(cssSelector(".notification_status_error .button")).click();
    }

}