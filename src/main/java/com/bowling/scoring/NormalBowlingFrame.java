package com.bowling.scoring;

/**
 * Frame that has no strike and is not last Frame
 */
public class NormalBowlingFrame implements BowlingFrame {
    private int[] values = new int[2];
    private BowlingFrame nextFrame;

    NormalBowlingFrame(int firstBall) {
        values[0] = firstBall;
        values[1] = -1;
    }

    public void setNextFrame(BowlingFrame frame) {
        this.nextFrame = frame;
    }

    public BowlingFrame getNextFrame() {
        return nextFrame;
    }

    public void addNextBall(int secondBall) throws BowlingScoreException {
        if(values[0] + secondBall > 10) throw new BowlingScoreException("Number of pins is > 10");
        values[1] = secondBall;
    }
    
    @Override
    public int nextFrameBall() {
        return values[0];
    }

    @Override
    public int nextFrame2Balls() {
        return values[0] + values[1];
    }

    public boolean has2values() {
        return values[1] != -1;
    }

    @Override
    public boolean isComplete() {
        return values[1] > -1;
    }

    @Override
    public int frameScore() {
        if(((values[0] + values[1]) < 10 || getNextFrame() != null)) {
            int total = values[1] >= 0? values[0] + values[1] : values[0];
            // total == 10 => Spare, the next ball is added to the score
            return total == 10 ? 10 + getNextFrame().nextFrameBall() : total;
        }
        return -1;
    }

    @Override
    public int[] values() {
        return values;
    }
}