package Strategies.WinningStartegies;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    private final Map<Character,Integer> leftdiag = new HashMap<>();
    private final Map<Character,Integer> rightdiag = new HashMap<>();
    @Override
    public boolean checkwinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        //Condition for left diagonal -> row == col
        if(row == col){
            if(!leftdiag.containsKey(aChar)){
                leftdiag.put(aChar,0);
            }
            leftdiag.put(aChar,leftdiag.get(aChar)+1);
        }

        //Condition for right diagonal -> row + col == N-1
        if(row+col == board.getDimension() - 1){
            if (!rightdiag.containsKey(aChar)){
                rightdiag.put(aChar,0);
            }
            rightdiag.put(aChar,rightdiag.get(aChar)+1);
        }

        if(row == col && leftdiag.get(aChar) == board.getDimension()){
            return true;
        }

        if(row+col == board.getDimension()-1 && rightdiag.get(aChar) == board.getDimension()){
            return true;
        }

        return false;
    }
}
