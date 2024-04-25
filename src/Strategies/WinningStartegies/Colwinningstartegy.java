package Strategies.WinningStartegies;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class Colwinningstartegy implements WinningStrategy{
    private final Map<Integer, HashMap<Character,Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkwinner(Board board, Move move) {

        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!colMaps.containsKey(col)){
            colMaps.put(col,new HashMap<>());
        }

        Map<Character,Integer> colMap = colMaps.get(col);

        if(!colMap.containsKey(aChar)){
            colMap.put(aChar,0);
        }

        colMap.put(aChar,colMap.get(aChar)+1);

        if(colMap.get(aChar).equals(board.getDimension())){
            return true;
        }

        return false;
    }
}
