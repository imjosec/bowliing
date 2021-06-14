package com.bowling.scoring;

import java.util.List;

public interface BowlingScoreRenderer {
    String render(List<BowlingFrame> frames);
}
