package Factories;

import Models.BotDifficultylevel;
import Strategies.Botplayyingstrategy.BotplayStrategy;
import Strategies.Botplayyingstrategy.Easybotplaystrategy;
import Strategies.Botplayyingstrategy.Hardbotplaystartegy;
import Strategies.Botplayyingstrategy.Mediumbotplaystrategy;

public class Botplaystrategyfactory {
    public static BotplayStrategy getBotplaystrategyfactory(BotDifficultylevel botDifficultylevel){
        if(botDifficultylevel.equals(BotDifficultylevel.Easy)){
            return new Easybotplaystrategy();
        }else if(botDifficultylevel.equals(BotDifficultylevel.Medium)){
            return new Mediumbotplaystrategy();
        }else {
            return new Hardbotplaystartegy();
        }
    }
}
