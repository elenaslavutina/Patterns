package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;

public class CardOrderTest {

    PageObject page = new PageObject();

    private DataCreator dataCreator = new DataCreator();
    private String City = dataCreator.getCity();
    private String Name = dataCreator.getFullName();
    private String Phone = dataCreator.getPhone();

    private String FirstDate = dataCreator.getDate(3);
    private String SecondDate = dataCreator.getDate(5);
    private String InvalidDate = dataCreator.getDate(0);

    @Test
    void shouldSendRequest() {
        page.setCity(City);
        page.setDate(FirstDate);
        page.setFullName(Name);
        page.setPhone(Phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");
    }

    @Test
    void shouldConfirmWhenDateChanged() {
        page.setCity(City);
        page.setDate(FirstDate);
        page.setFullName(Name);
        page.setPhone(Phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(SecondDate);
        page.apply();
        page.confirm();
        page.verifyPopup("Успешно!");

    }

    @Test
    void shouldNotGiveNewDateWhenInvalidDate() {

        page.setCity(City);
        page.setDate(FirstDate);
        page.setFullName(Name);
        page.setPhone(Phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(InvalidDate);
        page.apply();
        page.verifyWarning("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldShowWarningForWrongCity() {
        page.setCity("Кисловодск");
        page.setDate(FirstDate);
        page.setFullName(Name);
        page.setPhone(Phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyWarning("Доставка в выбранный город недоступна");
    }
}