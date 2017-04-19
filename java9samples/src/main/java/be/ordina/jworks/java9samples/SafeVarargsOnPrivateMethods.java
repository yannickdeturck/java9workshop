package be.ordina.jworks.java9samples;

import java.util.Arrays;
import java.util.List;

/**
 * SafeVarargs on private methods.
 *
 * @author Yannick De Turck
 */
public class SafeVarargsOnPrivateMethods {
    public static void main(String[] args) {
        foo(List.of("a", "b"), List.of("c", "d", "e"));
        String[] strings = arrayOfTwo("a", "b");
        for (String string : strings) {
            System.out.println(string);
            // java.lang.ClassCastException: java.base/[Ljava.lang.Object; cannot be cast to java.base/[Ljava.lang.String;
        }
    }

    @SafeVarargs
    static <T> T[] asArray(T... args) {
        return args;
    }

    static <T> T[] arrayOfTwo(T a, T b) {
        return asArray(a, b);
    }


    @SafeVarargs
    private static void foo(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        array[0] = tmpList; // Semantically invalid, but compiles without warnings
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
    }
}
