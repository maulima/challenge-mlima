package service;

import model.Game;
import model.GroundRules;
import model.PlayerMove;

import java.util.List;

public interface GameMoves {
    PlayerMove processPlayerMove(String input);

    void processMove(List<Game> games, PlayerMove playerMove);

    void processFrames(List<Game> games, GroundRules rules);

    void calculateTotal(List<Game>games,int totalAvailablePin);

    void printGames(List<Game>games,int totalFrames);
}
