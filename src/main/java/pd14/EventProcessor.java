package pd14;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {
    public static void process(List<Event> events, List<Predicate<Event>> filters,
                         Function<Event, String> formatter, Consumer<String> output) {
        events.forEach(event -> {
            boolean passed = true;

            for (Predicate<Event> filter : filters) {
                if (!filter.test(event)) {
                    passed = false;
                    break;
                }
            }

            if (passed) {
                output.accept(formatter.apply(event));
            }
        });

    }
}
