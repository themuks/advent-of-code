package com.themuks.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/themuks/day2/in.txt"))) {
            int sum = br.lines().mapToInt(solver::solve).sum();
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}