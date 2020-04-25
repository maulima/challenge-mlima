import model.GroundRules;
import service.GameMoves;
import service.GameMovesImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class main {

    public static void main(String[] args){

        BowlingGame challengeGame = new BowlingGame();
        GameMoves gameMoves = new GameMovesImpl();
        GroundRules rules = new GroundRules();

        String fileInput = System.getProperty("user.dir") +"/src/main/resources/input-test.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
//            FIRST: process all input player moves from file
            stream.forEach(p -> challengeGame.addPlayerMoves(gameMoves.processPlayerMove(p)));
//            SECOND: Create games per player
            challengeGame.getPlayerMoves().forEach(playerMove -> gameMoves.processMove(challengeGame.getGames(),playerMove));
//            THIRD: Create each frame per player
            gameMoves.processFrames(challengeGame.getGames(),rules);
//            FOURTH: Calculate total for each frame
            gameMoves.calculateTotal(challengeGame.getGames(),rules.getTotalAvailablePin());
//            FINALLY: print results as expected
            gameMoves.printGames(challengeGame.getGames(),rules.getTotalFrames());
        }catch (IOException e){
            System.out.println("Invalid file path");
        }
    }

}
