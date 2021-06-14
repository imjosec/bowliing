package com.bowling.scoring;

import java.util.ArrayList;
import java.util.List;

public class BowlingFrameParserImpl implements BowlingFrameParser {
    private final ArrayList<BowlingFrame> frames = new ArrayList<>(10);

    /**
     * Returns the list of frames, using the result per each ball as parameters
     */
    @Override
    public List<BowlingFrame> parseFrames(int ball) throws BowlingScoreException {
        final int frameNumber = frames.size();
        final BowlingFrame lastFrame = frames.isEmpty()? null : frames.get(frameNumber - 1);
        if(frameNumber == 10 && lastFrame.isComplete()) {
            throw new BowlingScoreException("Frames completed");
        }
        if(lastFrame != null && !lastFrame.isComplete()) {
            // while frame is not complete add the ball result to the frame
            completeFrame(ball, lastFrame);
        } else {
            // previous frame was complete, create a new one
            final BowlingFrame nextFrame = newFrame(ball, frameNumber);
            if (lastFrame != null) lastFrame.setNextFrame(nextFrame);
            frames.add(nextFrame);
        }
        return frames;
    }

    private static void completeFrame(int ball, BowlingFrame currentFrame) throws BowlingScoreException {
        currentFrame.addNextBall(ball);
    }

    private static BowlingFrame newFrame(int ball, int frameNumber) {
        final BowlingFrame nextFrame;
        if(frameNumber < 9) {
            nextFrame = ball == 10 ? new StrikeBowlingFrame() : new NormalBowlingFrame(ball);
        } else {
            nextFrame = new LastBowlingFrame(ball);
        }
        return nextFrame;
    }
}