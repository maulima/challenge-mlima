import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {

    static List<Game> games = new ArrayList<>();

    static Integer totalShotsPerFrame = 2;
    static Integer totalAvailablePin = 10;
    static Integer totalFramesRegularMoves = 9;
    static Integer totalFrames = 10;

    public static void main(String[] args){
        String fileInput = System.getProperty("user.dir") +"/src/main/resources/input-test.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
            stream.forEach(p -> processPlayerMove(p));
            processFrames();
            calulateTotal();
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
//        Print numbers of frames
        List<Integer> framesTitle = new ArrayList<>();
        for(int frameNumber =1;frameNumber<=totalFrames;frameNumber++){
            framesTitle.add(frameNumber);
        }
        System.out.println("Frame\t\t"+framesTitle.stream().map(String :: valueOf).collect(Collectors.joining("\t\t")));
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
                    if(originalShot.equals("F")){
                        hasFail = true;
                    }
                }

                if(game.frames.size() == totalFramesRegularMoves){
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
    static public void calulateTotal(){
        for(Game game : games){
            int index = 0;
            int total = 0;
            for(Frame frame: game.frames){
                if(frame.shots.size()==1 ){
                    total = setTotalFrame(game,frame,index,total);
                }else if(frame.shots.stream().collect(Collectors.summingInt(Integer::intValue)) == 10){
                    total = setTotalFrame(game,frame,index,total);
                    index+=1;
                }else {
                    total += frame.shots.stream().collect(Collectors.summingInt(Integer::intValue));
                    frame.setTotal(total);
                    index+=1;
                }
                index+=1;
            }
        }
    }
    static int setTotalFrame(Game game,Frame frame,int index,int total) {
        List<Integer> subList = game.getMoves().subList(index, index + 3);
        total += subList.stream().collect(Collectors.summingInt(Integer::intValue));
        frame.setTotal(total);
        return total;
    }
}
