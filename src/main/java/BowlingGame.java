import model.Frame;
import model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BowlingGame {
    public List<Game> games;

    public BowlingGame() {
        this.games = new ArrayList<>();
    }

    public void processPlayerMove(String input){
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

    public void printGames(int totalFrames){
//        Print numbers of frames
        System.out.println("Frame\t\t"+createFrameTitles(totalFrames).stream().map(String :: valueOf).collect(Collectors.joining("\t\t")));
        for(Game game: this.games){
            System.out.println(game.toString());
        }

    }
    public void processFrames(int totalFramesRegularMoves,int totalAvailablePin,int totalShotsPerFrame){
        for(Game game: this.games){
            List<Integer> shots = new ArrayList<>();
            int totalPin = 0;
            boolean hasFail = false;
            for(Integer index =0 ; index < game.getMoves().size(); index ++){
                int value = game.getMoves().get(index);
                totalPin += value;
                shots.add(value);
                if (value == 0){
                    String originalShot = game.getOriginalMoves().get(index);
                    if(originalShot.equals("F")){
                        hasFail = true;
                    }
                }
                if(game.getFrames().size() == totalFramesRegularMoves){
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
    public void caculateTotal(int totalAvailablePin){
        for(Game game : this.games){
            int index = 0;
            int total = 0;
            for(Frame frame: game.getFrames()){
                if(frame.getShots().size()==1 ){
                    total = setTotalFrame(game,frame,index,total);
                }else if(frame.getShots().stream().collect(Collectors.summingInt(Integer::intValue)) == totalAvailablePin){
                    total = setTotalFrame(game,frame,index,total);
                    index+=1;
                }else {
                    total += frame.getShots().stream().collect(Collectors.summingInt(Integer::intValue));
                    frame.setTotal(total);
                    index+=1;
                }
                index+=1;
            }
        }
    }
    private int getPinFalls(String input){
        try{
            return Integer.parseInt(input);
        }catch ( NumberFormatException e){
            return 0;
        }
    }
    private void processMove(String player,Integer move,String originalMove){
        Game search = this.games.stream().filter(game -> game.getPlayerName().equals(player)).findFirst().orElse(null);
        if ( search != null){
            this.games.remove(search);
        } else {
            search = new Game(player);
        }
        search.addOriginalMove(originalMove);
        search.addMove(move);
        this.games.add(search);
    }
    private List<Integer> createFrameTitles(int totalFrames){
        List<Integer> framesTitle = new ArrayList<>();
        for(int frameNumber =1;frameNumber<=totalFrames;frameNumber++){
            framesTitle.add(frameNumber);
        }
        return framesTitle;
    }
    private Frame createFrame(List<Integer>shots, boolean hasFail){
        Frame frame = new Frame();
        frame.setShots(shots);
        frame.setHasFail(hasFail);
        return frame;
    }
    private int setTotalFrame(Game game,Frame frame,int index,int total) {
        List<Integer> subList = game.getMoves().subList(index, index + 3);
        total += subList.stream().collect(Collectors.summingInt(Integer::intValue));
        frame.setTotal(total);
        return total;
    }
}
