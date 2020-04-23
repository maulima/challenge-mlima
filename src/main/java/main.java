import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class main {

    static List<Game> games = new ArrayList<>();

    static Integer totalShotsPerFrame = 2;
    static Integer totalAvailablePin = 10;
    static Integer totalFramesLimit = 9;

    public static void main(String[] args){
        String fileInput = System.getProperty("user.dir") +"/src/main/resources/input-test.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
            stream.forEach(p -> processPlayerMove(p));
            processFrames();
            printGames();
        }catch (IOException e){

        }
    }
    static void processPlayerMove(String input){
        StringTokenizer playerMove = new StringTokenizer(input);
        if(playerMove.countTokens() == 2){
            String name = playerMove.nextToken();
            String originalPinFall = playerMove.nextToken();
            int pinFalls = getPinFalls(originalPinFall);
            processMove(name,pinFalls,originalPinFall);
        }else{
            System.out.println("Invalid Player move");
        }
    }
    static int getPinFalls(String input){
        try{
            return Integer.parseInt(input);
        }catch ( NumberFormatException e){
            return 0;
        }
    }
    static void processMove(String player,Integer move,String originalMove){
        Game search = games.stream().filter(game -> game.getPlayerName().equals(player)).findFirst().orElse(null);
        if ( search != null){
            games.remove(search);
        } else {
            search = new Game(player);
        }
        search.addOriginalMove(originalMove);
        search.addMove(move);
        games.add(search);
    }
    static void printGames(){

        for(Game game: games){
            game.toString();
        }

    }

    static void processFrames(){
        for(Game game: games){
            List<Integer> shots = new ArrayList<>();
            int totalPin = 0;
            boolean hasFail = false;
            for(Integer index =0 ; index < game.getMoves().size(); index ++){
                int value = game.getMoves().get(index);
                totalPin += value;
                shots.add(value);

                if (value == 0){
                    String originalShot = game.originalMoves.get(index);
//                    System.out.println("Frame has error at frame: "+game.frames.size() + " at position: " + index + "value of the original shot: "+originalShot);
                    if(originalShot.equals("F")){
                        hasFail = true;
                    }
                }

                if(game.frames.size() == totalFramesLimit){
//                    Condition for special last frame
                    continue;
                }
                if(totalPin < totalAvailablePin  && shots.size() < totalShotsPerFrame ){
                    continue;
                }else{
                    game.addFrame(createFrame(shots,hasFail));
                    shots = new ArrayList<>();
                    totalPin = 0;
                    hasFail = false;
                }
            }
            game.addFrame(createFrame(shots,hasFail));

        }
    }
    static Frame createFrame(List<Integer>shots,boolean hasFail){
        Frame frame = new Frame();
        frame.setShots(shots);
        frame.setHasFail(hasFail);
        return frame;
    }
}
