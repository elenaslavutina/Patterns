package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.Data;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
public class DataCreator {
    DataCreator() {
    }

    public static String getFullName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getPhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static String getDate(int dayFromToday) {
        LocalDate today = LocalDate.now();
        LocalDate date = today.plusDays(dayFromToday);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return date.format(format);
    }

    public static String getCity() {
        Random rnd = new Random();
        List<String> list = Arrays.asList(
                "Москва", "Санкт-Петербург","Нижний Новгород", "Новосибирск", "Тамбов",
                "Тула", "Смоленск", "Псков", "Симферополь", "Уфа", "Омск", "Волгоград");

        return list.get(rnd.nextInt(list.size()));
    }

}



