package aoc.year2025.day01;

import aoc.Day;

import java.util.List;

public class Day01 extends Day {

    static {
        currentDay = buildCurrentDay(new Object() {});
    }

    @Override
    public String part1(List<String> input) {
        return input.isEmpty() ? "" : input.getFirst();
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : input.getFirst();
    }

}
