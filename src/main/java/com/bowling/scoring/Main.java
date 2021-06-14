package com.bowling.scoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BowlingFrameParserImpl parser = new BowlingFrameParserImpl();
    private static BowlingScoreRendererImpl renderer = new BowlingScoreRendererImpl();
    private static BowlingProcessor inputIterator = new BowlingProcessor(
            new BufferedReader(new InputStreamReader(System.in)), System.out, parser, renderer);

    public static void main(String[] args) throws IOException {
        inputIterator.iterateOverInput();
    }
}
