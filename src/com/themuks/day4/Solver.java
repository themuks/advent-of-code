package com.themuks.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Solver {
    public int solve(List<String> cardLines) {
        // Part one
        /*int points = 0;

        for (int actualNumber : card.actualNumbers()) {
            for (int winningNumber : card.winningNumbers()) {
                if (actualNumber == winningNumber) {
                    points += points == 0 ? 1 : points;
                    break;
                }
            }
        }

        return points;*/
        List<Card> cards = new ArrayList<>();

        for (String cardLine : cardLines) {
            int cardId = Integer.parseInt(cardLine.substring(5, cardLine.indexOf(':')).strip());
            List<Integer> winningNumbers = Arrays.stream(
                            cardLine.substring(cardLine.indexOf(':') + 1, cardLine.indexOf('|'))
                                    .strip()
                                    .split("\\s+")
                    )
                    .map(Integer::parseInt)
                    .toList();
            List<Integer> actualNumbers = Arrays.stream(
                            cardLine.substring(cardLine.indexOf('|') + 1)
                                    .strip()
                                    .split("\\s+")
                    )
                    .map(Integer::parseInt)
                    .toList();

            Card card = new Card(cardId, winningNumbers, actualNumbers);
            cards.add(card);
        }

        Stack<Card> cardStack = new Stack<>();
        cardStack.addAll(cards);

        for (int j = 0; j < cardStack.size(); j++) {
            Card card = cardStack.get(j);
            int winningNumbersCount = 0;
            for (int actualNumber : card.actualNumbers()) {
                for (int winningNumber : card.winningNumbers()) {
                    if (actualNumber == winningNumber) {
                        winningNumbersCount += 1;
                        break;
                    }
                }
            }

            IntStream.range(1, winningNumbersCount + 1)
                    .forEach(
                            i -> cards.stream()
                                    .filter(c -> c.id() == card.id() + i)
                                    .findFirst()
                                    .ifPresent(cardStack::add)
                    );
        }

        return cardStack.size();
    }
}
