package Strategies.Botplayyingstrategy;

import Models.Board;
import Models.Move;

public interface BotplayStrategy {
    Move makemove(Board board);
}
