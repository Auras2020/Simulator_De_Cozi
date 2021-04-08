package model;

import view.SimulationFrame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable{

    public int timeLimit; //max processing time
    private int currentTime=0;
    public int maxServiceTime;
    public int minServiceTime;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    private String x="";
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private File outFile;
    private SimulationFrame view;
    private List<Client> clienti=new ArrayList<>();

    public SimulationManager(File outFile, SimulationFrame view){
        this.numberOfClients=Integer.parseInt(view.getText1().getText());
        this.numberOfServers=Integer.parseInt(view.getText2().getText());
        this.timeLimit=Integer.parseInt(view.getText3().getText());
        this.minArrivalTime=Integer.parseInt(view.getText4().getText());
        this.maxArrivalTime=Integer.parseInt(view.getText5().getText());
        this.minServiceTime=Integer.parseInt(view.getText6().getText());
        this.maxServiceTime=Integer.parseInt(view.getText7().getText());
        this.outFile = outFile;
        this.view=view;

        try
        {
            this.outFile.createNewFile();
        }
        catch(IOException ex) {
            System.out.println("File does not exists and could not be created ");
            return;
        }

        generateRandomClients();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
    }

    public void generateRandomClients(){
        Random r;
        for(int i=0; i<numberOfClients; i++){
            r=new Random();
            int arrivalTime=minArrivalTime+r.nextInt(maxArrivalTime-minArrivalTime);
            int serviceTime=minServiceTime+r.nextInt(maxServiceTime-minServiceTime);
            Client client=new Client(i, arrivalTime, serviceTime);
            clienti.add(client);
        }
        Collections.sort(clienti, new SortClienti());
    }

    public FileWriter createFWriter(){
        FileWriter fWriter;
        try{
            fWriter=new FileWriter(this.outFile.toString());
        }catch (IOException ex){
            System.out.println("error creating fWriter");
            return null;
        }
        return fWriter;
    }

    public int avgWaitingTime(Client client){
        int averageWaitingTime=0;
        BlockingQueue<Client> copie=new ArrayBlockingQueue<>(numberOfClients);
        for(Coada c: scheduler.getCozi()){
            if(c.getClienti().contains(client)){
                copie.addAll(c.getClienti());
                break;
            }
        }
        while(copie.size()>0){
            averageWaitingTime+=copie.peek().getServiceTime();
            copie.poll();
        }
        return averageWaitingTime;
    }

    public void setSimulationInfo(FileWriter fWriter, double averageWaitingTime, double avgServiceTime, int peakHour){
        averageWaitingTime/=numberOfClients;
        avgServiceTime/=numberOfClients;

        view.setServiceTime(avgServiceTime + "");
        view.setWaitingTime(averageWaitingTime + "");
        view.setPeakHour(peakHour + "");

        try{
            String n=System.getProperty("line.separator");
            fWriter.write("Average service time is: " + avgServiceTime + n);
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }
        try{
            String n=System.getProperty("line.separator");
            fWriter.write("Average waiting time is: " + averageWaitingTime + n);
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }
        try{
            String n=System.getProperty("line.separator");
            fWriter.write("Peak hour is: " + peakHour + n);
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }
        try{
            fWriter.close();
        }catch(IOException ex) {
            System.out.println("Could not close file");
        }
    }

    public void writeToFile(FileWriter fWriter, String mesaj, String x, int currentTime){
        try{
            String n=System.getProperty("line.separator");
            fWriter.write(mesaj + n);

            view.setContent(x);
            view.setTime("Time: " + currentTime + "  ");
        }catch(IOException ex) {
            System.out.println("Could not write to file");
        }
    }

    public void sleep(){
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        FileWriter fWriter=createFWriter();
        double averageWaitingTime=0, avgServiceTime=0;
        int peakHour=0, sumMax=-1;
        while(currentTime<timeLimit){
            if(clienti.size()>0) {
                while (clienti.get(0).getArrivalTime() == currentTime) {
                    avgServiceTime+=clienti.get(0).getServiceTime();
                    scheduler.changeStrategy(selectionPolicy);
                    scheduler.dispatchTask(clienti.get(0));
                    averageWaitingTime+=avgWaitingTime(clienti.get(0));
                    clienti.remove(0);
                    if (clienti.size() == 0) {
                        break;
                    }
                }
            }
            int sum=0, nr=0;
            for(Coada c: scheduler.getCozi()){
                if(c.getClienti().size()>0){
                    nr++;
                }
                sum+=c.getClienti().size();
            }
            if(sum>sumMax){
                sumMax=sum;
                peakHour=currentTime;
            }
            view.setQueuesOpened(nr + "");
            view.setClientsArrived(numberOfClients-clienti.size() + "");
            view.setClientsWaiting(sum + "");

            x+=toString();
            writeToFile(fWriter, toString(), x, currentTime);
            if(clienti.size()==0 && scheduler.coziGoale(scheduler.getCozi())==1){
                break;
            }
            currentTime++;
            sleep();
        }
        scheduler.stop();
        setSimulationInfo(fWriter, averageWaitingTime, avgServiceTime, peakHour);
    }

    public String toString(){
        String x="Time: " + currentTime + ". Waiting clients:";
        for(Client c: clienti){
            x+=c.toString() + ";";
        }
        x+="\n" + scheduler.toString() + "\n\n";
        return x;
    }

}
