package com.huntingcube;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class StreamsImpl {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Abdul", "Swetank", "Abdul", "Dravit", "Amit", "Dravit", "Abdul", "Chintoo");

        list.stream()
                .filter(i -> Collections.frequency(list, i) > 1)
                .collect(Collectors.groupingBy((i) -> {list, Collections.frequency(list, i) > 1}))
                .forEach(System.out::println);

        /*list.stream()
                .filter()*/


        Map<String, Integer> duplicates = new LinkedHashMap<>();

        for (String str : list) {
            if (duplicates.containsKey(str)) {
                duplicates.put(str, duplicates.get(str) + 1);
            } else {
                duplicates.put(str, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : duplicates.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
