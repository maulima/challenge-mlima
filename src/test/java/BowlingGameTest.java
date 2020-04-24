import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BowlingGameTest {
    private BowlingGame game;
    private GroundRules rules;

    @Before
    public void initialGame(){
        game = new BowlingGame();
        rules = new GroundRules();
    }
    @Test
    public void initialTest() {
        assertEquals(0, game.getGames().size());
    }
    @Test
    public void processMoveTest() {
        String input = "Carl 10";
        game.processPlayerMove(input);
        assertEquals(1, game.getGames().size());
    }
    @Test
    public void checkStrikeRule(){
        String strikeShot = "Carl 10";
//        Simulate a perfect game
        for (int index = 0 ;index<=12;index++){
            game.processPlayerMove(strikeShot);
        }
        game.processFrames(rules.getTotalFramesRegularMoves(),rules.getTotalAvailablePin(),rules.getTotalShotsPerFrame());
        game.caculateTotal(rules.getTotalAvailablePin());
        assertSame(30,game.getGames().get(0).getFrames().get(0).getTotal());
    }
    @Test
    public void checkSpareRule(){
        String firstShot = "Carl 7";
        String secondShot = "Carl 3";
        game.processPlayerMove(firstShot);
        game.processPlayerMove(secondShot);
        String strikeShot = "Carl 10";
//        Complete game with strike shots
        for (int index = 1 ;index<=12;index++){
            game.processPlayerMove(strikeShot);
        }
        game.processFrames(rules.getTotalFramesRegularMoves(),rules.getTotalAvailablePin(),rules.getTotalShotsPerFrame());
        game.caculateTotal(rules.getTotalAvailablePin());
        assertSame(20,game.getGames().get(0).getFrames().get(0).getTotal());
    }


}
