package info;

import com.github.javafaker.Faker;
import lombok.Value;
import java.util.Calendar;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }
    @Value
    public static class CardNumber {
        String number;
    }

    public static CardNumber getApprovedCard() {
        return new CardNumber("4444 4444 4444 4441");
    }

    public static CardNumber getDeclinedCard() {
        return new CardNumber("4444 4444 4444 4442");
    }

    public static CardNumber getRandomCard() {
        return new CardNumber("1234 5678 9101 1121");
    }

    @Value
    public static class CardInfo {
        String month;
        String year;
        String holder;
        String pin;
    }

    public static CardInfo card() {
        return new CardInfo(generatedMonth(), generatedYear(), generatedHolder(), generatedPin());
    }

    private static String generatedMonth() {
        Random random = new Random();
        String num;
        int number = random.nextInt(12);
        if (number < 10) {
            num = String.valueOf(number);
            num = new StringBuilder(num).insert(0, "0").toString();
            return num;
        } else return String.valueOf(number);
    }

    private static String generatedYear() {
        int y = Calendar.getInstance().get(Calendar.YEAR) - 1999;
        return Integer.toString(y);
    }

    private static String generatedHolder() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    private static String generatedPin() {
        Faker faker = new Faker();
        int num = faker.number().numberBetween(101, 999);
        return Integer.toString(num);
    }
}
