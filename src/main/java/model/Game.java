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
        StringBuilder result = new StringBuilder();

        result.append(this.getPlayerName()+"\n");
        result.append("Pinfalls\t"+this.getFrames().stream().map(String :: valueOf).collect(Collectors.joining("\t"))+"\n");
        result.append("Score\t\t"+this.getFrames().stream().map(e -> e.toStringTotal() ).collect(Collectors.toList()).stream().collect(Collectors.joining("\t\t"))+"\n");

        return result.toString();
    }
}
