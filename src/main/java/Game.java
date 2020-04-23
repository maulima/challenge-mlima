import java.util.ArrayList;
import java.util.List;

public class Game {
    String playerName;
    List<Integer> moves;

    public List<String> getOriginalMoves() {
        return originalMoves;
    }

    List<String> originalMoves;
    List<Frame> frames;

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
        System.out.println("**************");
        System.out.println("The player: "+this.playerName +" has "+ this.getMoves().size()+ " moves.");
        System.out.println("Values: "+this.getMoves().toString());
        System.out.println("The original moves: "+ this.getOriginalMoves().toString());
        this.frames.toString();
        System.out.println("**************");
        return null;
    }
}
