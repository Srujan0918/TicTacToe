package Strategies.WinningStartegies;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.Map;

public class Rowwinningstrategy implements WinningStrategy{
    private final Map<Integer, HashMap<Character,Integer>> rowMaps = new HashMap<>();
    @Override
    public boolean checkwinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row,new HashMap<>());
        }
        Map<Character,Integer> currRowMap = rowMaps.get(row);

        if(!currRowMap.containsKey(aChar)){
            currRowMap.put(aChar,0);
        }
        currRowMap.put(aChar,currRowMap.get(aChar)+1);
        return currRowMap.get(aChar).equals(board.getDimension());
    }
}
