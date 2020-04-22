import java.util.ArrayList;
import java.util.List;

public class Game {
    String playerName;
    List<Integer> moves;
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

    public Game(String playerName) {
        this.playerName = playerName;
        this.moves = new ArrayList<>();
        this.frames = new ArrayList<>();
    }
    @Override
    public String toString(){
        return "The player: "+this.playerName +" has "+ this.getMoves().size()+ " moves. values: "+this.getMoves().toString();
    }
}
