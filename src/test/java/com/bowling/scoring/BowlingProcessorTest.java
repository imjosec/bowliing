package com.bowling.scoring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BowlingProcessorTest {
    @Mock
    BufferedReader reader;
    @Mock
    PrintStream writer;
    @Mock
    BowlingFrame frame;
    @Mock
    BowlingFrameParser parser;
    @Mock
    BowlingScoreRenderer renderer;
    @InjectMocks
    BowlingProcessor bowlingProcessor;


    @Test
    void successful() throws IOException, BowlingScoreException {
        when(reader.readLine()).thenReturn("10");
        List<BowlingFrame> bowlingFrames = getBowlingFrames();
        when(frame.isComplete()).thenReturn(false, true);
        when(parser.parseFrames(10)).thenReturn(bowlingFrames);
        when(renderer.render(bowlingFrames)).thenReturn("rendered");
        bowlingProcessor.iterateOverInput();
        InOrder inOrder = inOrder(writer);
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("rendered");
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("rendered");
        inOrder.verify(writer).println("End result");
        verifyNoMoreInteractions(reader, writer, frame, parser, renderer);
    }

    @Test
    void invalidInput() throws IOException, BowlingScoreException {
        when(reader.readLine()).thenReturn("x", "-1", "100", "10");
        List<BowlingFrame> bowlingFrames = getBowlingFrames();
        when(frame.isComplete()).thenReturn(true);
        when(parser.parseFrames(10)).thenReturn(bowlingFrames);
        when(renderer.render(bowlingFrames)).thenReturn("rendered");
        bowlingProcessor.iterateOverInput();
        InOrder inOrder = inOrder(writer);
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("Invalid Ball, try again");
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("Invalid Ball, try again");
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("Invalid Ball, try again");
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("rendered");
        inOrder.verify(writer).println("End result");
        verifyNoMoreInteractions(reader, writer, frame, parser, renderer);
    }

    @Test
    void invalidInputInParser() throws IOException, BowlingScoreException {
        when(reader.readLine()).thenReturn("10");
        List<BowlingFrame> bowlingFrames = getBowlingFrames();
        when(frame.isComplete()).thenReturn(true);
        when(parser.parseFrames(10)).thenThrow(new BowlingScoreException("ERROR")).thenReturn(bowlingFrames);
        when(renderer.render(bowlingFrames)).thenReturn("rendered");
        bowlingProcessor.iterateOverInput();
        InOrder inOrder = inOrder(writer);
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("ERROR");
        inOrder.verify(writer).print("Next Ball: > ");
        inOrder.verify(writer).println("rendered");
        inOrder.verify(writer).println("End result");
        verifyNoMoreInteractions(reader, writer, frame, parser, renderer);
    }

    private List<BowlingFrame> getBowlingFrames() {
        List<BowlingFrame> frames = Arrays.asList(frame, frame, frame, frame, frame, frame, frame, frame, frame, frame);
        return frames;
    }
}
