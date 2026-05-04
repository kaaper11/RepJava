package pd18;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.stream.Stream;

public class DateCalculator {

    public int age(LocalDate birtDate) {
        LocalDate today = LocalDate.now();
        return (birtDate.getMonth().getValue() >= today.getMonth().getValue() && birtDate.getDayOfMonth() >= today.getDayOfMonth() && birtDate.equals(today)) ?
                today.getYear() - birtDate.getYear() :
                today.getYear() - birtDate.getYear() - 1;
    }

    public LocalDate nextPayDay(LocalDate from) {
        LocalDate lastDayOfMonth = LocalDate.of(from.getYear(), from.getMonth(), from.lengthOfMonth());
        if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return lastDayOfMonth.minusDays(1);
        } else if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return lastDayOfMonth.minusDays(2);
        }
        return lastDayOfMonth;
    }

    public long businessDaysBetween(LocalDate from, LocalDate to) {
        return Stream.iterate(from, localDate -> localDate.plusDays(1))
                .limit(Duration.between(LocalDateTime.of(from.getYear(), from.getMonth().getValue(), from.getDayOfMonth(), 0, 0),
                        LocalDateTime.of(to.getYear(), to.getMonth().getValue(), to.getDayOfMonth(), 0, 0)).toDays())
                .filter(localDate -> localDate.getDayOfWeek() == DayOfWeek.SUNDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY)
                .count();
    }

    public ZonedDateTime convertTimezone(ZonedDateTime dt, String targetZone) {
        return dt.withZoneSameInstant(ZoneId.of(targetZone));
    }

    public String formatForLocale(LocalDateTime dt, String languageTag) {
        return dt.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag(languageTag)));
    }

    public boolean isValidDate(String input, String pattern) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern(pattern));
            return true;
        } catch (DateTimeParseException e) {
            System.err.println("Nieudane parsowania daty!");
            return false;
        }
    }
}
