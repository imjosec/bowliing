package com.bowling.scoring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingFrameParserImplTest {
    static Stream<Arguments> testNameResultParams() {
        return Stream.of(
            arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 2, 7}, 153), // Valid case
            arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 3, 7}, -1), // Invalid case
            arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 10, 3, 7}, 156), // Invalid case
            arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 1}, 145), // Valid case
            arguments(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0), // Valid case
            arguments(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 300), // Valid case
            arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 2, 7, 1}, -1) // More balls than expected
        );
    }

    BowlingFrameParserImpl parser = new BowlingFrameParserImpl();

    @ParameterizedTest(name = "#{index} - Test {1} ")
    @MethodSource("testNameResultParams")
    void test1(int[] input, int result) throws BowlingScoreException {
        if(result >= 0) {
            List<BowlingFrame> frames = parseFrames(input);
            assertEquals(result, frames.stream().mapToInt(BowlingFrame::frameScore).sum());
        } else {
            assertThrows(BowlingScoreException.class, () -> parseFrames(input));
        }
    }

    private List<BowlingFrame> parseFrames(int[] input) throws BowlingScoreException {
        List<BowlingFrame> frames = null;
        for(int i : input) {
            frames = parser.parseFrames(i);
        }
        return frames;
    }
}
