package com.themuks.day2;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    // Part one
    /*private static final int TOTAL_RED_CUBES = 12;
    private static final int TOTAL_GREEN_CUBES = 13;
    private static final int TOTAL_BLUE_CUBES = 14;*/

    public int solve(String gameStr) {

        Game game = parseGameString(gameStr);

        // Part one
        /*for (CubeSet cubeSet : game.cubeSets) {
            if (cubeSet.cubesRevealed.get(CubeType.RED) > TOTAL_RED_CUBES
                || cubeSet.cubesRevealed.get(CubeType.GREEN) > TOTAL_GREEN_CUBES
                || cubeSet.cubesRevealed.get(CubeType.BLUE) > TOTAL_BLUE_CUBES) {
                return 0;
            }
        }

        return game.id;*/

        // Part two
        int redMin = 0;
        int greenMin = 0;
        int blueMin = 0;

        for (CubeSet cubeSet : game.cubeSets) {
            Integer redCount = cubeSet.cubesRevealed.get(CubeType.RED);
            Integer greenCount = cubeSet.cubesRevealed.get(CubeType.GREEN);
            Integer blueCount = cubeSet.cubesRevealed.get(CubeType.BLUE);

            redMin = Math.max(redMin, redCount);
            greenMin = Math.max(greenMin, greenCount);
            blueMin = Math.max(blueMin, blueCount);
        }

        return redMin * greenMin * blueMin;
    }

    private Game parseGameString(String str) {
        Game game = new Game();

        game.id = Integer.parseInt(str.substring(str.indexOf(' ') + 1, str.indexOf(':')));

        String gamesStr = str.substring(str.indexOf(':') + 2);

        List<CubeSet> cubeSets = new ArrayList<>();

        while (gamesStr.indexOf(';') != -1) {
            String roundStr = gamesStr.substring(0, gamesStr.indexOf(';'));
            gamesStr = gamesStr.substring(gamesStr.indexOf(';') + 2);
            CubeSet cubeSet = parseRoundString(roundStr);
            cubeSets.add(cubeSet);
        }

        CubeSet cubeSet = parseRoundString(gamesStr);
        cubeSets.add(cubeSet);

        game.cubeSets = cubeSets;
        return game;
    }

    private CubeSet parseRoundString(String roundStr) {
        CubeSet cubeSet = new CubeSet();

        while (roundStr.indexOf(',') != -1) {
            String cubeStr = roundStr.substring(0, roundStr.indexOf(','));
            int cubeCount = Integer.parseInt(cubeStr.substring(0, cubeStr.indexOf(' ')));
            CubeType cubeType = CubeType.valueOf(cubeStr.substring(cubeStr.indexOf(' ') + 1).toUpperCase());

            cubeSet.cubesRevealed.replace(cubeType, cubeCount);

            roundStr = roundStr.substring(roundStr.indexOf(',') + 2);
        }

        int cubeCount = Integer.parseInt(roundStr.substring(0, roundStr.indexOf(' ')));
        CubeType cubeType = CubeType.valueOf(roundStr.substring(roundStr.indexOf(' ') + 1).toUpperCase());

        cubeSet.cubesRevealed.replace(cubeType, cubeCount);

        return cubeSet;
    }
}
