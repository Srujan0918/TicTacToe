import Controller.GameController;
import Exceptions.InvalidException;
import Models.*;
import Strategies.WinningStartegies.Colwinningstartegy;
import Strategies.WinningStartegies.DiagonalWinningStrategy;
import Strategies.WinningStartegies.Rowwinningstrategy;
import Strategies.WinningStartegies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidException {
        System.out.println("Game Starts!");

        Scanner scanner = new Scanner(System.in);

        GameController gameController = new GameController();

        int dimension = 3;

        List<Player> players = new ArrayList<>();
        players.add(
                new Player(new Symbol('X'),"Anand", PlayerType.Human)
        );

        players.add(
                new Bot(new Symbol('O'),"Scaler",PlayerType.BOT, BotDifficultylevel.Easy)
        );

        List<WinningStrategy> winningStrategies = List.of(
          new Rowwinningstrategy(),
          new Colwinningstartegy(),
          new DiagonalWinningStrategy()
        );

        Game game = gameController.startGame(dimension,players,winningStrategies);

        while (gameController.gamestatus(game).equals(Gamestatus.Inprogress)){
            gameController.printBoard(game);

            System.out.println("Do you want to Undo? y/n");
            String isundo = scanner.next();

            if(isundo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            System.out.println("Debug");
            gameController.makeMove(game);
            System.out.println("Debug");
        }

        System.out.println("Debug");

        gameController.printBoard(game);
        if(gameController.gamestatus(game).equals(Gamestatus.Ended)){
            System.out.println(gameController.getWinner(game).getName()+"has won the game.");
        }else{
            System.out.println("GAME DRAW");
        }
    }
}