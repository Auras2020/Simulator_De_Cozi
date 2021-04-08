package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable{

    private BlockingQueue<Client> clienti;
    private AtomicInteger waitingPeriod;
    private boolean open = true;

    public Coada(int nrMax){
        this.clienti=new ArrayBlockingQueue<>(nrMax);
        this.waitingPeriod=new AtomicInteger();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void addClient(Client client){
        clienti.add(client);
        waitingPeriod.addAndGet(client.getServiceTime());
    }

    @Override
    public void run() {
        while(open) {
            while (clienti.peek()!=null) {
                try {
                    Thread.sleep(5000);
                    waitingPeriod.getAndDecrement();
                    clienti.peek().setServiceTime(clienti.peek().getServiceTime() - 1);
                    if (clienti.peek().getServiceTime() == 0) {
                        clienti.remove(clienti.peek());
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            setOpen(false);
        }
    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public String toString(){
        String x;
        if(clienti.peek()==null || waitingPeriod.get()==0 || clienti.peek().getServiceTime()==0){
            x="closed";
        }
        else{
            x=clienti.toString();
        }
        return x;
    }
}
