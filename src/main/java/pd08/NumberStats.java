package pd08;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
public class NumberStats<T extends Number & Comparable<T>> {
    List<T> listOfNumber = new ArrayList<>();

    public static <T extends Number & Comparable<T>> NumberStats<T> of() {
        return new NumberStats<T>();
    }

    public void add(T number) {
        listOfNumber.add(number);
    }

    public T getMin() {
        getSorted();
        return listOfNumber.get(0);
    }

    public T getMax() {
        getSorted();
        return listOfNumber.get(listOfNumber.size() - 1);
    }

    public double getSum() {
        double sum = 0;
        for (T number : listOfNumber) {
            sum += number.doubleValue();
        }
        return sum;
    }

    public int getCount() {
        return listOfNumber.size();
    }

    public double getAverage() {
        double listSum = getSum();
        double listCount = getCount();
        return listSum != 0 ? listSum / listCount : 0;
    }

    private List<T> getSorted() {
        List<T> sortedListOfNumber = new ArrayList<>(listOfNumber);
        Collections.sort(sortedListOfNumber);
        return sortedListOfNumber;
    }
}
