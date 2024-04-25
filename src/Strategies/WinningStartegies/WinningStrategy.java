package Strategies.WinningStartegies;

import Models.Board;
import Models.Move;

public interface WinningStrategy {
    boolean checkwinner(Board board, Move move);
}
