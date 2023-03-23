package info;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardNumber {
        String number;
    }

    public static CardNumber approved() {
        return new CardNumber("4444 4444 4444 4441");
    }

    public static CardNumber declined() {
        return new CardNumber("4444 4444 4444 4442");
    }

    public static CardNumber random() {
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
        return new CardInfo("12", "27", "PAPANISTER VITALYA", "999");
    }

}
