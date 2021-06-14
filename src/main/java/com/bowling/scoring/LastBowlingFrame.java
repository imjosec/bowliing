package com.bowling.scoring;

/**
 * Last Frame
 */
class LastBowlingFrame implements BowlingFrame {
    private int[] values = new int[3];

    LastBowlingFrame(int firstBall) {
        values[0] = firstBall;
        values[1] = -1;
        values[2] = -1;
    }

    @Override
    public void addNextBall(int ball)  throws BowlingScoreException {
        if(values[1] == -1) {
            if(values[0] < 10 && values[0] + ball > 10) throw new BowlingScoreException("Number of pins is > 10");
            this.values[1] = ball;
        }
        else {
            if(values[0] == 10 && values[1] < 10 && values[1] + ball > 10)
                throw new BowlingScoreException("Number of pins is > 10");
            values[2] = ball;
        }
    }

    @Override
    public int nextFrameBall() {
        return values[0];
    }

    @Override
    public int nextFrame2Balls() {
        return values[0] + values[1];
    }

    @Override
    public boolean has2values() {
        return values[1] != -1;
    }

    @Override
    public boolean isComplete() {
        return values[2] > -1 || values[1] > -1 && (values[0] + values[1]) < 10;
    }

    @Override
    public int frameScore() {
        int total = values[0];
        if(values[1] >= 0) total+= values[1];
        if(values[2] >= 0) total+= values[2];
        return total;
    }

    @Override
    public int[] values() {
        return values;
    }
}