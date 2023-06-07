package ch.fhnw.clashofenergiespeicher.model.world;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScoreTest {

    public static int BASEPOINTS = 2777;
    Score score = new Score();

    @Test
    void testCalculateScore_minusE_1000kw_diff() {
        score.calculateScore(9000, 8000);
        assertEquals(score.getTotalScore(), BASEPOINTS - 600);
    }

    @Test
    void testCalculateScore_minusE_2000kw_diff() {
        score.calculateScore(9000, 7000);
        assertEquals(score.getTotalScore(), BASEPOINTS - 1400);
    }

    @Test
    void testCalculateScore_minusE_4000kw_diff() {
        score.calculateScore(9000, 5000);
        assertEquals(score.getTotalScore(), 0);
    }

    @Test
    void testCalculateScore_plusE_1000kw_diff() {
        score.calculateScore(9000, 10000);
        assertEquals(score.getTotalScore(), BASEPOINTS - 200);
    }

    @Test
    void testCalculateScore_plusE_4000kw_diff() {
        score.calculateScore(9000, 13000);
        assertEquals(score.getTotalScore(), BASEPOINTS - 800);
    }

    @Test
    void testCalculateScore_optimalE() {
        score.calculateScore(9000, 9000);
        assertEquals(score.getTotalScore(), BASEPOINTS);
    }

    @Test
    void testMaxScore() {
        // TODO
    }
}
