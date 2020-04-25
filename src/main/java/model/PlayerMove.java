package model;

public class PlayerMove {
    String playerName;
    String originalMove;
    int moveValue;

    public String getPlayerName() {
        return playerName;
    }


    public String getOriginalMove() {
        return originalMove;
    }

    public int getMoveValue() {
        return moveValue;
    }

    public PlayerMove(String playerName, String originalMove, int moveValue) {
        this.playerName = playerName;
        this.originalMove = originalMove;
        this.moveValue = moveValue;
    }

    public PlayerMove() {

    }
}
