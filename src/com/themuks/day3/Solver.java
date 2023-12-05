package com.themuks.day3;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public int solve(List<String> engineScheme) {
        List<LexemeAreaPair> engineNumbers = new ArrayList<>();
        List<LexemeAreaPair> engineSymbols = new ArrayList<>();

        for (int i = 0; i < engineScheme.size(); i++) {
            StringBuilder numberString = new StringBuilder();
            int numberX = -1;
            for (int j = 0; j < engineScheme.get(i).length(); j++) {
                char currentChar = engineScheme.get(i).charAt(j);
                if (Character.isDigit(currentChar)) {
                    if (numberX == -1) {
                        numberX = j;
                    }
                    numberString.append(currentChar);
                } else {
                    if (!numberString.isEmpty()) {
                        LexemeAreaPair pair = new LexemeAreaPair(
                                numberString.toString(),
                                new Area(numberX - 1, i - 1, numberX + numberString.length(), i + 1)
                        );
                        engineNumbers.add(pair);
                        numberString = new StringBuilder();
                        numberX = -1;
                    }
                }
                // Part one
                // if (currentChar != '.' && !Character.isDigit(currentChar)) {
                // Part two
                if (currentChar == '*') {
                    Area symbolArea = new Area(j - 1, i - 1, j + 1, i + 1);
                    engineSymbols.add(new LexemeAreaPair(String.valueOf(currentChar), symbolArea));
                }
            }
            if (!numberString.isEmpty()) {
                LexemeAreaPair pair = new LexemeAreaPair(
                        numberString.toString(),
                        new Area(numberX - 1, i - 1, numberX + numberString.length(), i + 1)
                );
                engineNumbers.add(pair);
            }
        }

        int resultSum = 0;

        System.out.println(engineNumbers);
        System.out.println(engineSymbols);

        for (LexemeAreaPair numberLexemePair : engineNumbers) {
            for (LexemeAreaPair symbolLexemePair : engineSymbols) {
                if (isOverlapping(numberLexemePair.area(), symbolLexemePair.area())) {
                    for (LexemeAreaPair secondNumberLexemePair : engineNumbers) {
                        if (!secondNumberLexemePair.equals(numberLexemePair)
                                && isOverlapping(secondNumberLexemePair.area(), symbolLexemePair.area())) {
                            int a = Integer.parseInt(numberLexemePair.lexeme());
                            int b = Integer.parseInt(secondNumberLexemePair.lexeme());
                            resultSum += a * b;
                        }
                    }
                }
            }
        }

        return resultSum / 2;
    }

    // Part one
    /*private boolean isSpecialSymbolAround(Area numberPosition, List<Area> symbolPositions) {
        for (Area symbolPosition : symbolPositions) {
            if (symbolPosition.x1() >= numberPosition.x1() - 1
                    && symbolPosition.y1() >= numberPosition.y1() - 1
                    && symbolPosition.x2() <= numberPosition.x2() + 1
                    && symbolPosition.y2() <= numberPosition.y2() + 1) {
                return true;
            }
        }
        return false;
    }*/

    private boolean isOverlapping(Area a, Area b) {
        return a.x1() < b.x2() && a.x2() > b.x1() && a.y1() < b.y2() && a.y2() > b.y1();
    }
}