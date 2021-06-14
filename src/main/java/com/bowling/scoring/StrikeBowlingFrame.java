package com.bowling.scoring;

/**
 * Frame that happened when one ball throws all the pins (10 pins)
 */
public class StrikeBowlingFrame implements BowlingFrame {
    private final int[] values = new int[]{10};
    private BowlingFrame nextFrame;

    public BowlingFrame getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(BowlingFrame frame) {
        this.nextFrame = frame;
    }

    @Override
    public int nextFrameBall() {
        return 10;
    }

    @Override
    public int nextFrame2Balls() {
        return 10 + getNextFrame().nextFrameBall();
    }

    @Override
    public int frameScore() {
        // for a strike, we need to add the next 2 balls to the score
        if(has2NextBalls())
            return 10 + getNextFrame().nextFrame2Balls();
        return -1;
    }

    public boolean has2NextBalls() {
        BowlingFrame nextFrame = getNextFrame();
        boolean nextIsStrike = nextFrame instanceof StrikeBowlingFrame;
        return nextFrame != null && (!nextIsStrike && nextFrame.has2values() || nextFrame.getNextFrame() != null);
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public int[] values() {
        return values;
    }
}
