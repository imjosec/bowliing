package com.bowling.scoring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BowlingScoreRendererImplTest {
    BowlingScoreRendererImpl bowlingScoreRenderer = new BowlingScoreRendererImpl();
    BowlingFrameParserImpl parser = new BowlingFrameParserImpl();

    static Stream<Arguments> testNameResultParams() {
        return Stream.of(
                arguments(new int[] {6, 2}, "|6|2|\n|8  |\n"),
                arguments(new int[] {6, 2, 7}, "|6|2|7| |\n" +
                                               "|8  |15 |\n"),
                arguments(new int[] {6, 2, 7, 0}, "|6|2|7|-|\n" +
                                                  "|8  |15 |\n"),
                arguments(new int[] {6, 2, 10}, "|6|2| X |\n" +
                                                "|8  |   |\n"),
                arguments(new int[] {6, 2, 10, 1}, "|6|2| X |1| |\n" +
                                                   "|8  |   |   |\n"),
                arguments(new int[] {6, 2, 10, 1, 3}, "|6|2| X |1|3|\n" +
                                                      "|8  |22 |26 |\n"),
                arguments(new int[] {6, 2, 10, 1, 0}, "|6|2| X |1|-|\n" +
                                                      "|8  |19 |20 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 2, 7},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|8|/|7|\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 153 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8, 1},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|8|1| |\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 145 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 8},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|8| | |\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 144 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 10, 1, 2},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|X|1|2|\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 149 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 10, 10, 2},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|X|X|2|\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 158 |\n"),
                arguments(new int[] {6, 2, 7, 2, 3, 4, 8, 2, 9, 0, 10, 10, 10, 6, 3, 10, 10, 10},
                        "|6|2|7|2|3|4|8|/|9|-| X | X | X |6|3|X|X|X|\n" +
                        "|8  |17 |24 |43 |52 |82 |108|127|136| 166 |\n"),
                arguments(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                        "| X | X | X | X | X | X | X | X | X |X| | |\n" +
                        "|30 |60 |90 |120|150|180|210|240|   |     |\n"),
                arguments(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                        "| X | X | X | X | X | X | X | X | X |X|X| |\n" +
                        "|30 |60 |90 |120|150|180|210|240|270| 290 |\n"),
                arguments(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                        "| X | X | X | X | X | X | X | X | X |X|X|X|\n" +
                        "|30 |60 |90 |120|150|180|210|240|270| 300 |\n")
        );
    }

    @ParameterizedTest(name = "#{index} - Test {0} ")
    @MethodSource("testNameResultParams")
    void test1(int[] input, String result) throws BowlingScoreException {
        assertEquals(result, bowlingScoreRenderer.render(parseFrames(input)));
    }

    private List<BowlingFrame> parseFrames(int[] input) throws BowlingScoreException {
        List<BowlingFrame> frames = null;
        for(int i : input) {
            frames = parser.parseFrames(i);
        }
        return frames;
    }
}
