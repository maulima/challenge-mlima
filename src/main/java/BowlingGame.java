import model.Game;
import model.PlayerMove;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    public List<Game> games;
    private List<PlayerMove> playerMoves;

    public List<PlayerMove> getPlayerMoves() {
        return playerMoves;
    }

    public void addPlayerMoves(PlayerMove playerMove) {
        this.playerMoves.add(playerMove);
    }

    public List<Game> getGames() {
        return games;
    }

    public BowlingGame() {
        this.games = new ArrayList<>();
        this.playerMoves = new ArrayList<>();
    }



}
