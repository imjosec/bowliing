package com.bowling.scoring;

interface BowlingFrame {

    default void addNextBall(int value) throws BowlingScoreException {
    }

    default void setNextFrame(BowlingFrame frame) {
    }

    default BowlingFrame getNextFrame() {
        return null;
    }

    /**
     * @return returns whether the frame has all the data, excluding what's required for frameScore
     */
    boolean isComplete();

    /**
     * @return the next ball after this frame
     */
    int nextFrameBall();

    /**
     * @return sum of the next 2 balls after this frame
     */
    int nextFrame2Balls();

    /**
     * @return whether has 2 valuesn
     */
    default boolean has2values() {
        return false;
    }

    /**
     * @return points calculated for the frame
     */
    int frameScore();

    /**
     * @return String representation of the Frame without the score
     */
    int[] values();
}