package com.themuks.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/themuks/day1/in.txt"))) {
            int sum = br.lines().mapToInt(Main::solve).sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int solve(String str) {
        Map<String, Integer> digits = new HashMap<>();
        digits.put("one", 1);
        digits.put("two", 2);
        digits.put("three", 3);
        digits.put("four", 4);
        digits.put("five", 5);
        digits.put("six", 6);
        digits.put("seven", 7);
        digits.put("eight", 8);
        digits.put("nine", 9);
        StringBuilder sb = new StringBuilder();

        Optional<Integer> result = findFirstDigitOccurrence(str, digits);

        result.ifPresent(sb::append);

        Map<String, Integer> reversedDigits = new HashMap<>();
        for (var entry : digits.entrySet()) {
            reversedDigits.put(new StringBuilder(entry.getKey()).reverse().toString(), entry.getValue());
        }

        result = findFirstDigitOccurrence(new StringBuilder(str).reverse().toString(), reversedDigits);

        result.ifPresent(sb::append);

        return Integer.parseInt(sb.toString());
    }

    private static Optional<Integer> findFirstDigitOccurrence(String str, Map<String, Integer> digits) {
        int lowestPosition = Integer.MAX_VALUE;
        Integer lowestDigit = null;

        // Find first text digit
        for (var key : digits.keySet()) {
            int position = str.indexOf(key);
            if (position != -1 && position < lowestPosition) {
                lowestPosition = position;
                lowestDigit = digits.get(key);
            }
        }

        // Find first number digit
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c) && lowestPosition > i) {
                lowestPosition = i;
                lowestDigit = Character.getNumericValue(c);
            }
        }

        return Optional.ofNullable(lowestDigit);
    }
}