package com.themuks.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver();
        List<String> lines = Files.readAllLines(Path.of("src/com/themuks/day3/in.txt"));
        int sum = solver.solve(lines);
        System.out.println(sum);
    }
}