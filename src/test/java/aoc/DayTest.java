package aoc;

import aoc.year2014.day01.FakeDay01;
import aoc.year2025.day01.Day01;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class DayTest {

    private ByteArrayOutputStream interceptedOut;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        interceptedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(interceptedOut));
    }

    @Test
    void printPart1() {

        Day day = new FakeDay01();
        day.printPart1();
        day.printPart2();

        assertThat(interceptedOut.toString())
            .containsIgnoringNewLines(
                "Day 1, Part 1: 123",
                "Day 1, Part 2: 456");
    }

    @Test
    void yearNumberTakenFromPackage() {
        assertThat(new Day01().year()).isEqualTo(2025);
    }

    @Test
    void dayNumberTakenFromPackage() {
        assertThat(new Day01().dayNumber()).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

}
