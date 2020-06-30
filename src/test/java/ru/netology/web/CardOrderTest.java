package ru.netology.web;


import org.junit.jupiter.api.Test;

public class CardOrderTest {

    InitialisationPage pageOrder = new InitialisationPage();

    private String city = DataCreator.getCity();
    private String name = DataCreator.getFullName();
    private String phone = DataCreator.getPhone();


    @Test
    void shouldSendRequest() {
        pageOrder.setCity(city);
        pageOrder.setDate(DataCreator.getDate(3));
        pageOrder.setFullName(name);
        pageOrder.setPhone(phone);
        pageOrder.agreeWithConditions();
        pageOrder.apply();
        pageOrder.verifyPopup("Успешно!");
    }

    @Test
    void shouldConfirmWhenDateChanged() {
        pageOrder.setCity(city);
        pageOrder.setDate(DataCreator.getDate(3));
        pageOrder.setFullName(name);
        pageOrder.setPhone(phone);
        pageOrder.agreeWithConditions();
        pageOrder.apply();
        pageOrder.verifyPopup("Успешно!");

        pageOrder.setDate(DataCreator.getDate(5));
        pageOrder.apply();
        pageOrder.confirm();
        pageOrder.verifyPopup("Успешно!");

    }

    @Test
    void shouldNotGiveNewDateWhenInvalidDate() {

        pageOrder.setCity(city);
        pageOrder.setDate(DataCreator.getDate(3));
        pageOrder.setFullName(name);
        pageOrder.setPhone(phone);
        pageOrder.agreeWithConditions();
        pageOrder.apply();
        pageOrder.verifyPopup("Успешно!");

        pageOrder.setDate(DataCreator.getDate(0));
        pageOrder.apply();
        pageOrder.verifyWarning("Заказ на выбранную дату невозможен");
    }

    @Test
    void shouldShowWarningForWrongCity() {
        pageOrder.setCity("Кисловодск");
        pageOrder.setDate(DataCreator.getDate(3));
        pageOrder.setFullName(name);
        pageOrder.setPhone(phone);
        pageOrder.agreeWithConditions();
        pageOrder.apply();
        pageOrder.verifyWarning("Доставка в выбранный город недоступна");
    }
}