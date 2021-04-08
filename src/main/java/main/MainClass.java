package main;

import model.SimulationManager;
import view.SimulationFrame;

import java.io.File;

public class MainClass {

    public static void main(String[] args){

        SimulationFrame view=new SimulationFrame();
        view.getText1().setText(4 + "");
        view.getText2().setText(2 + "");
        view.getText3().setText(60 + "");
        view.getText4().setText(2 + "");
        view.getText5().setText(7 + "");
        view.getText6().setText(2 + "");
        view.getText7().setText(4 + "");
        SimulationManager simulation=new SimulationManager(new File("out_file.txt"), view);

        Thread mainThread=new Thread(simulation);
        mainThread.start();
        try{
            mainThread.join();
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
