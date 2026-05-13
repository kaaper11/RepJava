package pd08;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        NumberStats<Integer> numberStatsInteger = new NumberStats<>();
        NumberStats<Double> numberStatsDouble = new NumberStats<>();
        NumberStats<BigDecimal> numberStatsBigDecimal = new NumberStats<>();

        numberStatsInteger.add(10);
        numberStatsInteger.add(1);
        numberStatsInteger.add(5);
        System.out.println(numberStatsInteger);
        System.out.println(numberStatsInteger.getMin());
        System.out.println(numberStatsInteger.getMax());
        System.out.println(numberStatsInteger.getSum());
        System.out.println(numberStatsInteger.getCount());
        System.out.println(numberStatsInteger.getAverage());

        numberStatsDouble.add(11.23);
        numberStatsDouble.add(5.1);
        numberStatsDouble.add(9.794);
        System.out.println(numberStatsDouble);
        System.out.println(numberStatsDouble.getMin());
        System.out.println(numberStatsDouble.getMax());
        System.out.println(numberStatsDouble.getSum());
        System.out.println(numberStatsDouble.getCount());
        System.out.println(numberStatsDouble.getAverage());

        numberStatsBigDecimal.add(new BigDecimal("10.23"));
        numberStatsBigDecimal.add(new BigDecimal("5.1"));
        numberStatsBigDecimal.add(new BigDecimal("9.794"));
        System.out.println(numberStatsBigDecimal);
        System.out.println(numberStatsBigDecimal.getMin());
        System.out.println(numberStatsBigDecimal.getMax());
        System.out.println(numberStatsBigDecimal.getSum());
        System.out.println(numberStatsBigDecimal.getCount());
        System.out.println(numberStatsBigDecimal.getAverage());
    }
}
