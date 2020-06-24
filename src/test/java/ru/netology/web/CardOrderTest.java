package ru.netology.web;


import org.junit.jupiter.api.Test;

public class CardOrderTest {

    PageObject page = new PageObject();

    private DataCreator dataCreator = new DataCreator();
    private String city = dataCreator.getCity();
    private String name = dataCreator.getFullName();
    private String phone = dataCreator.getPhone();


    @Test
    void shouldSendRequest() {
        page.setCity(city);
        page.setDate(dataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");
    }

    @Test
    void shouldConfirmWhenDateChanged() {
        page.setCity(city);
        page.setDate(dataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(dataCreator.getDate(5));
        page.apply();
        page.confirm();
        page.verifyPopup("Успешно!");

    }

    @Test
    void shouldNotGiveNewDateWhenInvalidDate() {

        page.setCity(city);
        page.setDate(dataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyPopup("Успешно!");

        page.setDate(dataCreator.getDate(0));
        page.apply();
        page.verifyWarning("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldShowWarningForWrongCity() {
        page.setCity("Кисловодск");
        page.setDate(dataCreator.getDate(3));
        page.setFullName(name);
        page.setPhone(phone);
        page.agreeWithConditions();
        page.apply();
        page.verifyWarning("Доставка в выбранный город недоступна");
    }
}