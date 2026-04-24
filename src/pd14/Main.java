package pd14;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Predicate<Event>> predicates = List.of(event -> event.getName().length() > 5, event -> event.getNumberOfParticipants() > 20, event -> EventType.MUSIC.equals(event.getType()));
        Function<Event, String> onlyName = Event::getName;
        Function<Event, String> typeAndNumberOfParticipants = event -> event.getType() + " " + event.getNumberOfParticipants();
        Function<Event, String> toStringMapper = Event::toString;
        Consumer<String> output = System.out::println;

        List<Event> events = List.of(
                Event.of("Koncert", 100, EventType.MUSIC),
                Event.of("Mecz", 20, EventType.SPORT),
                Event.of("Spektakl", 5, EventType.OTHER),
                Event.of("Trasa koncertowa", 120, EventType.MUSIC)
                );

        EventProcessor.process(events, predicates, onlyName, output);


    }
}
