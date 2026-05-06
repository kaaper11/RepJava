package pd18;

import java.time.*;

public class Main {
    public static void main(String[] args) {
        DateCalculator dateCalculator = new DateCalculator();

        System.out.println(dateCalculator.age(LocalDate.of(2020, 12, 11)));
        System.out.println(dateCalculator.nextPayDay(LocalDate.of(2026, 5, 4)));
        System.out.println(dateCalculator.businessDaysBetween(LocalDate.of(2020, 12, 11), LocalDate.of(2026, 5, 4)));
        System.out.println(dateCalculator.convertTimezone(ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "Europe/Warsaw"));
        System.out.println(dateCalculator.formatForLocale(LocalDateTime.of(2020, 12, 11, 3, 5), "PL"));
        System.out.println(dateCalculator.isValidDate("04-10-2020", "dd-MM-yyyy"));
    }
}
