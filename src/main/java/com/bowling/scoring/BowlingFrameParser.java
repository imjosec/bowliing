package com.bowling.scoring;

import java.util.List;

public interface BowlingFrameParser {
    List<BowlingFrame> parseFrames(int ball) throws BowlingScoreException;
}
