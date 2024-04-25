package Strategies.Botplayyingstrategy;

import Models.Board;
import Models.Cell;
import Models.Cellstate;
import Models.Move;

import java.util.List;

public class Easybotplaystrategy implements BotplayStrategy{
    @Override
    public Move makemove(Board board) {
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellstate().equals(Cellstate.Empty)){
                    return new Move(null,cell);
                }
            }
        }
        System.out.println("Debug");
        return null;
    }
}
