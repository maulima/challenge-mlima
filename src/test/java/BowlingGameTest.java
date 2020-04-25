import model.GroundRules;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.GameMoves;
import service.GameMovesImpl;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BowlingGameTest {
    private BowlingGame game;
    private GroundRules rules;
    private GameMoves gameMoves;

    @Before
    public void initialGame(){
        game = new BowlingGame();
        rules = new GroundRules();
        gameMoves = new GameMovesImpl();

    }
    @Test
    public void initialTest() {
        assertEquals(0, game.getGames().size());
    }
    @Test
    public void processMoveTest() {
        String input = "Carl 10";
        game.addPlayerMoves(gameMoves.processPlayerMove(input));
        assertEquals(1, game.getPlayerMoves().size());
    }
    @Test
    public void checkStrikeRule(){
        String strikeShot = "Carl 10";
//        Simulate a perfect game
        for (int index = 0 ;index<=12;index++){
            game.addPlayerMoves(gameMoves.processPlayerMove(strikeShot));
        }
        game.getPlayerMoves().stream().forEach(p->gameMoves.processMove(game.getGames(),p));
        gameMoves.processFrames(game.getGames(),rules);
        gameMoves.calculateTotal(game.getGames(),rules.getTotalAvailablePin());
        assertSame(30,game.getGames().get(0).getFrames().get(0).getTotal());
    }
    @Test
    public void checkSpareRule(){
        String firstShot = "Carl 7";
        String secondShot = "Carl 3";
        game.addPlayerMoves(gameMoves.processPlayerMove(firstShot));
        game.addPlayerMoves(gameMoves.processPlayerMove(secondShot));
        String strikeShot = "Carl 10";
//        Complete game with strike shots
        for (int index = 1 ;index<=12;index++){
            game.addPlayerMoves(gameMoves.processPlayerMove(strikeShot));
        }
        game.getPlayerMoves().stream().forEach(p->gameMoves.processMove(game.getGames(),p));
        gameMoves.processFrames(game.getGames(),rules);
        gameMoves.calculateTotal(game.getGames(),rules.getTotalAvailablePin());
        assertSame(20,game.getGames().get(0).getFrames().get(0).getTotal());
    }


}
