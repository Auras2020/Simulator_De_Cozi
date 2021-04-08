package model;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public int addTask(List<Coada> cozi, Client client) {

        int min=Integer.MAX_VALUE, min1=0, nr=0;
        for(Coada c: cozi) {
            if(c.getWaitingPeriod().get()<min){
                min=c.getWaitingPeriod().get();
                min1=nr;
            }
            nr++;
        }
        for(Coada c: cozi){
            if(c.getWaitingPeriod().get()==min){
                c.addClient(client);
                break;
            }
        }
        return min1;
    }
}
