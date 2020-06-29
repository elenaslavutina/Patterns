package ru.netology.web;


import org.junit.jupiter.api.Test;

public class CardOrderTest {

    InitialisationPage page = new InitialisationPage();

    private String city = DataCreator.getCity();
    private String name = DataCreator.getFullName();
    private String phone = DataCreator.getPhone();


    @Test
    void shouldSendRequest() {
        page.setCity(city);
        page.setDate(DataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");
    }

    @Test
    void shouldConfirmWhenDateChanged() {
        page.setCity(city);
        page.setDate(DataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(DataCreator.getDate(5));
        page.apply();
        page.confirm();
        page.verifyPopup("Успешно!");

    }

    @Test
    void shouldNotGiveNewDateWhenInvalidDate() {

        page.setCity(city);
        page.setDate(DataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(DataCreator.getDate(0));
        page.apply();
        page.verifyWarning("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldShowWarningForWrongCity() {
        page.setCity("Кисловодск");
        page.setDate(DataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyWarning("Доставка в выбранный город недоступна");
    }
}