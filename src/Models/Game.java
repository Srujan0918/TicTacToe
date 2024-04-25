package Models;

import Exceptions.InvalidException;
import Strategies.WinningStartegies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Gamestatus gamestatus;
    private Player winner;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimension,List<Player> players,List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gamestatus = Gamestatus.Inprogress;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Gamestatus getGamestatus() {
        return gamestatus;
    }

    public void setGamestatus(Gamestatus gamestatus) {
        this.gamestatus = gamestatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printBoard(){
        board.printboard();
    }
    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()){
            return false;
        }

        if(!board.getBoard().get(row).get(col).getCellstate().equals(Cellstate.Empty)){
            return false;
        }

        return true;
    }

    public void makeMove() throws InvalidException {
        Player currentplayer = players.get(nextMovePlayerIndex);

        System.out.println("This is "+currentplayer.getName()+"'s move.");

        Move move = currentplayer.makeMove(board);

        if(!validateMove(move)){
            throw new InvalidException("Invalid Move, Please Retry");
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellstate(Cellstate.Filled);
        cell.setPlayer(currentplayer);

        Move finalMove = new Move(currentplayer,cell);
        moves.add(finalMove);

        nextMovePlayerIndex = (nextMovePlayerIndex+1)%players.size();

        if(checkWinner(finalMove)){
            winner = currentplayer;
            gamestatus = Gamestatus.Ended;
        }else if(moves.size() == board.getDimension() * board.getDimension()){
            gamestatus = Gamestatus.Draw;
        }
    }

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkwinner(board,move)){
                return true;
            }
        }
        return false;
    }
    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        //private Board board;
//        private List<Move> moves;
//        private Gamestatus gamestatus;
//        private Player winner;
//        private int nextMovePlayerIndex;
        private Builder(){
            this.players=new ArrayList<>();
            this.dimension=0;
            this.winningStrategies=new ArrayList<>();
        }

        private void validateBotCount(){
            int count=0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    count += 1;
                    if(count > 1){
                        throw new RuntimeException("Only one BOT is allowed per game");
                    }
                }
            }
        }

        private void validateUniqueSymbols(){
            Set<Character> symbolset = new HashSet<>();
            for(Player player : players){
                symbolset.add(player.getSymbol().getaChar());
            }
            if(symbolset.size() != dimension-1){
                throw new RuntimeException("Every player Should have a unique symbol");
            }
        }

        private void validations(){
            validateBotCount();
            validateUniqueSymbols();
        }

        public Game build(){
            validations();
            return new Game(dimension,players,winningStrategies);
        }
        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }
}
