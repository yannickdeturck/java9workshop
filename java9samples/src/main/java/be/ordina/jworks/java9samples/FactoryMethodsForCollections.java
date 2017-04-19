package be.ordina.jworks.java9samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Factory methods for collections.
 *
 * @author Yannick De Turck
 */
public class FactoryMethodsForCollections {
    public static void main(String[] args) {
        // Java 8
        List<String> list = new ArrayList<>();
        list.add("why");
        list.add("hello");
        list.add("there");
        List<String> immutableList = Collections.unmodifiableList(list);

        // Java 9
        List<String> emptyList = List.of();
        List<String> aList = List.of("foo", "bar");
        Map<String, String> map = Map.of("key1", "value1", "key2", "value2");
        Map<String, String> mapOfEntries = Map.ofEntries(Map.entry("key1", "value1"),
                Map.entry("key2", "value2"));
    }
}
