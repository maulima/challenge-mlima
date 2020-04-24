import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class main {

    public static void main(String[] args){
        BowlingGame challengeGame = new BowlingGame();
        GroundRules rules = new GroundRules();
        String fileInput = System.getProperty("user.dir") +"/src/main/resources/input-test.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
            stream.forEach(p -> challengeGame.processPlayerMove(p));
            challengeGame.processFrames(rules.getTotalFramesRegularMoves(),rules.getTotalAvailablePin(),rules.getTotalShotsPerFrame());
            challengeGame.caculateTotal(rules.getTotalAvailablePin());
            challengeGame.printGames(rules.getTotalFrames());
        }catch (IOException e){
            System.out.println("Invalid file path");
        }
    }

}
