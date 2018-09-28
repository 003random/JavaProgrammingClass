/**
 * File to test several stuff
 *
 * @author Ruben Bos
 */

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        Set<Integer> numbers = Set.of(2, 4, 6, 8);
//
//        strings.stream()
//                .filter(s -> s.startsWith("c"))
//                .map(String::toUpperCase)
//                .sorted()
//                .forEach(s -> System.out.println(s));
//
//        strings.stream()
//                .findFirst()
//                .ifPresent(System.out::println);
//
//        numbers.stream()
//                .takeWhile(n -> n % 2 == 0)
//                .forEach(System.out::println);

//        IntStream.rangeClosed(13, 15).forEach(s->System.out.print(s +" "));
//        IntStream.range(13,15).forEach(s->System.out.print(s +" "));
//        System.out.print(IntStream.rangeClosed(1,10).sum());
//        IntStream.of(13,4,15,2,8).sorted().forEach(s->System.out.print(s +" "));

        Stream.of(1, 2, 3, 4, 5).mapToInt(x -> x).mapToObj(x -> Integer.toString(x)).takeWhile(x -> x.equals("3")).forEach(x -> System.out.println(x + "a"));

    }
}
