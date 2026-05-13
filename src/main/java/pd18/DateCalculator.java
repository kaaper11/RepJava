package pd18;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.stream.Stream;

public class DateCalculator {

    public long age(LocalDate birtDate) {
        return ChronoUnit.YEARS.between(birtDate, LocalDate.now());
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
                .limit(ChronoUnit.DAYS.between(from, to))
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
