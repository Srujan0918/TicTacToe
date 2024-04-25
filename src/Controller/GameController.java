package Controller;

import Exceptions.InvalidException;
import Models.Game;
import Models.Gamestatus;
import Models.Player;
import Strategies.WinningStartegies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        return Game.getBuilder().setDimension(dimension).setPlayers(players)
                .setWinningStrategies(winningStrategies).build();
    }

    public void makeMove(Game game) throws InvalidException {
        game.makeMove();
    }

    public Gamestatus gamestatus(Game game){
        return game.getGamestatus();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game){

    }
}
