package com.bowling.scoring;

import java.io.*;
import java.util.List;

/**
 * Iterates with the user, asking the result of throwing a ball to the pins
 */
public class BowlingProcessor {
    private final BufferedReader reader;
    private final PrintStream writer;
    private final BowlingFrameParser parser;
    private final BowlingScoreRenderer renderer;

    public BowlingProcessor(BufferedReader reader, PrintStream os, BowlingFrameParser parser, BowlingScoreRenderer renderer) {
        this.reader = reader;
        writer = os;
        this.parser = parser;
        this.renderer = renderer;
    }

    public void iterateOverInput() throws IOException {
        List<BowlingFrame> frames = null;
        do {
            int ball = readFromStdin();
            try {
                frames = parser.parseFrames(ball);
                writer.println(renderer.render(frames));
            } catch (BowlingScoreException e) {
                writer.println(e.getMessage());
            }
        } while(frames == null || frames.size() < 10 || !frames.get(9).isComplete());
        writer.println("End result");
    }

    private int readFromStdin() throws IOException {
        for(;;) {
            writer.print("Next Ball: > ");
            String str = reader.readLine();
            try {
                int ball = Integer.parseInt(str.trim());
                if(ball < 0 || ball > 10) writer.println("Invalid Ball, try again");
                else return ball;
            } catch (NumberFormatException e) {
                writer.println("Invalid Ball, try again");
            }
        }
    }
}
