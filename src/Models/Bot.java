package Models;

import Factories.Botplaystrategyfactory;
import Strategies.Botplayyingstrategy.BotplayStrategy;

public class Bot extends Player{
    private BotDifficultylevel botDifficultylevel;
    private BotplayStrategy botplayStrategy;

    public Bot(Symbol symbol,String name,PlayerType playerType,BotDifficultylevel botDifficultylevel){
        super(symbol,name,playerType);
        this.botDifficultylevel = botDifficultylevel;
        this.botplayStrategy = Botplaystrategyfactory.getBotplaystrategyfactory(botDifficultylevel);
    }

    public BotDifficultylevel getBotDifficultylevel() {
        return botDifficultylevel;
    }

    public void setBotDifficultylevel(BotDifficultylevel botDifficultylevel) {
        this.botDifficultylevel = botDifficultylevel;
    }

    public BotplayStrategy getBotplayStrategy() {
        return botplayStrategy;
    }

    public void setBotplayStrategy(BotplayStrategy botplayStrategy) {
        this.botplayStrategy = botplayStrategy;
    }

    @Override
    Move makeMove(Board board){
        return botplayStrategy.makemove(board);
    }
}
