package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private String playerName;
    private List<Integer> moves;
    private List<String> originalMoves;
    private List<Frame> frames;

    public List<String> getOriginalMoves() {
        return originalMoves;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Integer> getMoves() {
        return moves;
    }

    public void addMove(Integer move){
        this.moves.add(move);
    }

    public void addOriginalMove(String move){
        this.originalMoves.add(move);
    }

    public void addFrame(Frame frame){
        this.frames.add(frame);
    }

    public Game(String playerName) {
        this.playerName = playerName;
        this.moves = new ArrayList<>();
        this.frames = new ArrayList<>();
        this.originalMoves = new ArrayList<>();
    }


    @Override
    public String toString(){
        System.out.println(this.getPlayerName());
        System.out.println("Pinfalls\t"+this.getFrames().stream().map(String :: valueOf).collect(Collectors.joining("\t")));
        List<String> scoreList = this.getFrames().stream().map(e -> e.toStringTotal() ).collect(Collectors.toList());
        System.out.println("Score\t\t"+scoreList.stream().collect(Collectors.joining("\t\t")));

        return null;
    }
}
