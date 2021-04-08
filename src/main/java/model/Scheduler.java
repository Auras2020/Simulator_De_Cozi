package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Coada> cozi;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private List<Thread> threads;

    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer=maxTasksPerServer;
        this.cozi=new ArrayList<>(maxNoServers);
        this.threads=new ArrayList<>(maxNoServers);
        for(int i=0; i<maxNoServers; i++){
            cozi.add(new Coada(maxTasksPerServer));
            Thread thread=new Thread(cozi.get(i));
            threads.add(thread);
            threads.get(i).start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        else if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }

    public void dispatchTask(Client client){
        int min=strategy.addTask(cozi, client);
        if(!cozi.get(min).isOpen()){
            cozi.get(min).setOpen(true);
            Thread thread=new Thread((cozi.get(min)));
            threads.set(min, thread);
            threads.get(min).start();
        }
    }

    public void stop()
    {
        for(Coada q : cozi)
        {
            q.setOpen(false);
        }
    }

    public int coziGoale(List<Coada> cozi){
        for(Coada c: cozi){
            if(c.getClienti().size()!=0){
                return 0;
            }
        }
        return 1;
    }

    public List<Coada> getCozi() {
        return cozi;
    }

    public String toString(){
        String x="";
        for(Coada c: cozi){
            int index=cozi.indexOf(c)+1;
            x+=" Queue " + index + ": " + c.toString() + ";";
        }
        return x;
    }
}
