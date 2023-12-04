package com.themuks.day2;

import java.util.HashMap;
import java.util.Map;

public class CubeSet {
    public Map<CubeType, Integer> cubesRevealed = new HashMap<>();

    public CubeSet() {
        cubesRevealed.put(CubeType.RED, 0);
        cubesRevealed.put(CubeType.GREEN, 0);
        cubesRevealed.put(CubeType.BLUE, 0);
    }
}