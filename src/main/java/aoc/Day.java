package aoc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public abstract class Day {

    private static final Pattern PACKAGE_NAME_CONVENTION = Pattern.compile("aoc.year(?<year>\\d{4}).day(?<day>\\d{2})");
    protected static Day currentDay;
    private final int year;
    private final int dayNumber;

    protected Day() {
        Matcher matcher = PACKAGE_NAME_CONVENTION.matcher(this.getClass().getPackageName());
        if (matcher.matches()) {
            this.year = Integer.parseInt(matcher.group("year"));
            this.dayNumber = Integer.parseInt(matcher.group("day"));
        }
        else throw new RuntimeException("The year and day number could not be extracted from the package name. The format should be `aoc.yearYYYY.dayDD` where YYYY is the four digit year, and DD is the two digit day.");
    }

    public abstract String part1(List<String> input);

    public abstract String part2(List<String> input);

    private List<String> loadInput() {
        String fileName = String.format("%04d/day%02d.txt", this.year(), this.dayNumber());

        InputStream inputForDay = ClassLoader.getSystemResourceAsStream(fileName);
        if (Objects.isNull(inputForDay)) {
            throw new IllegalArgumentException("Can’t find data for day using filename: " + fileName + ". Did you forget to put the file in the resources directory?");
        }
        try (BufferedReader r = new BufferedReader(new InputStreamReader(inputForDay))) {
            return r.lines().collect(toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public static void main(String[] unused) {
        if (currentDay == null) {
            throw new RuntimeException("You need to initialise currentDay in a static initialiser block in the child class");
        }
        currentDay.printPart1();
        currentDay.printPart2();
    }

    void printPart2() {
        System.out.println("Day " + this.dayNumber() + ", Part 2: " + this.part2(this.loadInput()));
    }

    void printPart1() {
        System.out.println("Day " + this.dayNumber() + ", Part 1: " + this.part1(this.loadInput()));
    }

    protected int year() {
        return this.year;
    }

    protected int dayNumber() {
        return this.dayNumber;
    }

    protected static Day buildCurrentDay(Object o) {
        try {
            Class<? extends Day> childDayClass = o.getClass().getEnclosingClass().asSubclass(Day.class);
            return childDayClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Failed to determine current day. Make sure an object declared and initialised in the child day class is passed in, and that the child class has a noarg constructor available (such as the default one).", e);
        }
    }
}
