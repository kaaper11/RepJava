package zl08;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        Pair<Integer, List<Integer>> para = Pair.of(10, List.of(1, 2, 3));
        Pair<Integer, List<Integer>> para1 = Pair.of(10, List.of(1, 2, 3));

//        System.out.println(para);//toString
//        System.out.println(para.getFirst());
//        System.out.println(para.getSecond());
//        System.out.println(para.swap());
//        System.out.println(para.equals(para1));
//        System.out.println(para.hashCode());
//        System.out.println(para1.hashCode());

        System.out.println(TransforemUtils.transformAll(List.of("haha", "ha"), new UpperCaseTransformer()));
    }
}
