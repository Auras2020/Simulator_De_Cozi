package model;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public int addTask(List<Coada> cozi, Client client) {
        int min=Integer.MAX_VALUE, min1=0, nr=0;
        for(Coada c: cozi){
            if(c.getClienti().size()<min){
                min=c.getClienti().size();
                min1=nr;
            }
            nr++;
        }
        for(Coada c: cozi){
            if(c.getClienti().size()==min){
                c.addClient(client);
                break;
            }
        }
        return min1;
    }
}
