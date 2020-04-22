import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class main {

    static List<Game> games = new ArrayList<>();

    public static void main(String[] args){
        String fileInput = System.getProperty("user.dir") +"/src/main/resources/input-test.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileInput))){
            stream.forEach(p -> processPlayerMove(p));
            printGames();
        }catch (IOException e){

        }
    }
    static String processPlayerMove(String input){
        StringTokenizer playerMove = new StringTokenizer(input);
        if(playerMove.countTokens() == 2){
            String name = playerMove.nextToken();
            int pinFalls = getPinFalls(playerMove.nextToken());
            processMove(name,pinFalls);

        }else{
            System.out.println("Invalid Player move");
        }
        return null;
    }
    static int getPinFalls(String input){
        try{
            return Integer.parseInt(input);
        }catch ( NumberFormatException e){
            return 0;
        }
    }
    static void processMove(String player,Integer move){
        Game search = games.stream().filter(game -> game.getPlayerName().equals(player)).findFirst().orElse(null);

        if ( search != null){
            games.remove(search);
        } else {
            search = new Game(player);

        }
        search.addMove(move);
        games.add(search);
    }
    static void printGames(){
        System.out.println("******");
        for(Game game: games){
            System.out.println(game.toString());
        }
        System.out.println("******");
    }
}
