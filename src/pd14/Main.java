package pd14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Predicate<Event>> predicates = List.of(event -> event.getName().length() > 5, event -> event.getNumberOfParticipants() > 20, event -> event.getType().equals(TypeOfEvent.MUSIC));
        Function<Event, String> onlyName = Event::getName;
        Function<Event, String> typeAndNumberOfParticipants = event -> event.getType() + " " + event.getNumberOfParticipants();
        Function<Event, String> allEvent = Event::toString;
        Consumer<String> output = System.out::println;

        List<Event> events = List.of(
                Event.of("Koncert", 100, TypeOfEvent.MUSIC),
                Event.of("Mecz", 20, TypeOfEvent.SPORT),
                Event.of("Spektakl", 5, TypeOfEvent.OTHER),
                Event.of("Trasa koncertowa", 120, TypeOfEvent.MUSIC)
                );

        EventProcessor.process(events, predicates, onlyName, output);


    }
}
