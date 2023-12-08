package com.themuks.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver();
        List<String> lines = Files.readAllLines(Path.of("src/com/themuks/day4/in.txt"));
        int result = solver.solve(lines);
        System.out.println(result);
    }
}