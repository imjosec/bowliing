package com.bowling.scoring;

import java.util.List;

public class BowlingScoreRendererImpl implements BowlingScoreRenderer {

    @Override
    public String render(List<BowlingFrame> frames) {
        StringBuilder sb = new StringBuilder();
        for(BowlingFrame frame : frames) {
            renderHeader(frame.values(), sb);
        }
        sb.append("|\n");
        int totalScore = 0;
        boolean validPrevScore = true;
        for(BowlingFrame frame : frames) {
            int score = frame.frameScore();
            if(validPrevScore && score >= 0) {
                totalScore += score;
                sb.append("|");
                if(frame.values().length == 3) sb.append(" ");
                sb.append(totalScore);
                if(totalScore < 10) sb.append("  ");
                else if(totalScore < 100) sb.append(" ");
            } else {
                validPrevScore = false;
                sb.append("|   ");
                if(frame.values().length == 3) sb.append(" ");
            }
            if(frame.values().length == 3) sb.append(" ");
        }
        sb.append("|\n");
        return sb.toString();
    }

    private static void renderHeader(int[] values, StringBuilder sb) {
        if(values.length == 1) {
            sb.append("| X ");
        } else if(values.length == 2) {
            if(values[0] + values[1] == 10) {
                sb.append("|").append(values[0]).append("|/");
            } else {
                sb.append("|").append(toChar(values[0])).append("|").append(toChar(values[1]));
            }
        } else {
            if(values[0] + values[1] == 10) {
                sb.append("|").append(values[0]).append("|/|").append(toChar(values[2]));
            } else {
                sb.append("|").append(toChar(values[0]))
                    .append("|").append(toChar(values[1]))
                    .append("|").append(toChar(values[2]));
            }
        }
    }

    private static char toChar(int v) {
        if(v == 0) return '-';
        else if(v == -1) return ' ';
        else if(v == 10) return 'X';
        else return (char) ('0' + v);
    }
}