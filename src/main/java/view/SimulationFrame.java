package view;

import model.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SimulationFrame extends JFrame implements ActionListener{
    //components of the first frame
    private JFrame frame1;
    private JFrame frame2;

    private JLabel title;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;

    private JButton start;

    //components of the second frame
    private JTextField time;
    private JTextPane content;
    private JScrollPane pane;

    private JLabel clients1;
    private JLabel clients2;
    private JLabel queues;
    private JLabel time1;
    private JLabel time2;
    private JLabel hour;

    private JTextField clientsWaiting;
    private JTextField clientsArrived;
    private JTextField queuesOpened;
    private JTextField waitingTime;
    private JTextField serviceTime;
    private JTextField peakHour;

    public SimulationFrame(){
        frame1=new JFrame("Simulation Setup");
        frame1.setBackground(Color.white);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title=new JLabel("Simulation Setup");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(title);

        label1=new JLabel("Number of clients: ");
        label2=new JLabel("Number of queues: ");
        label3=new JLabel("Simulation time: ");
        text1=new JTextField();
        text2=new JTextField();
        text3=new JTextField();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1, 10, 10));
        panel2.add(label1);
        panel2.add(text1);
        panel2.add(label2);
        panel2.add(text2);
        panel2.add(label3);
        panel2.add(text3);

        label4=new JLabel("Minimum and maximum arrival time: ");
        label5=new JLabel("Minimum and maximum waiting time: ");
        text4=new JTextField();
        text5=new JTextField();
        text6=new JTextField();
        text7=new JTextField();

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 3, 10, 10));
        panel3.add(label4);
        panel3.add(text4);
        panel3.add(text5);
        panel3.add(label5);
        panel3.add(text6);
        panel3.add(text7);

        start=new JButton("Start");

        JPanel panel4 = new JPanel();
        panel4.add(start);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        JPanel panel2_1 = new JPanel();
        JPanel panel2_2 = new JPanel();
        panel2_2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2_2.add(panel2);
        panel2_2.add(panel2_1);
        panel5.add(panel1);
        panel5.add( Box.createRigidArea(new Dimension(10,10)) );
        panel5.add(panel2_2);
        panel5.add( Box.createRigidArea(new Dimension(10,10)) );
        JPanel panel3_1 = new JPanel();
        JPanel panel3_2 = new JPanel();
        panel3_1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3_1.add(panel3);
        panel3_1.add(panel3_2);
        panel5.add(panel3_1);
        panel5.add( Box.createRigidArea(new Dimension(10,10)) );
        panel5.add(panel4);

        frame1.add(panel5);
        frame1.setVisible(true);
        frame1.pack();

        frame2=new JFrame("Simulation evolution");
        frame2.setBackground(Color.white);

        time=new JTextField("Time: 0");

        JPanel pan1 = new JPanel();
        pan1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan1.add(time);

        clients1=new JLabel("Clients arrived: ");
        clients2=new JLabel("Clients waiting: ");
        queues=new JLabel("Queues opened: ");
        time1=new JLabel("Average waiting time: ");
        time2=new JLabel("Average servicing time: ");
        hour=new JLabel("Peak hour: ");

        clientsArrived=new JTextField("0");
        clientsWaiting=new JTextField("0");
        queuesOpened=new JTextField("0");
        waitingTime=new JTextField("0");
        serviceTime=new JTextField("0");
        peakHour=new JTextField("0");

        JPanel pan2 = new JPanel();
        pan2.setLayout(new GridLayout(6, 2, 10, 10));
        pan2.add(clients1);
        pan2.add(clientsArrived);
        pan2.add(clients2);
        pan2.add(clientsWaiting);
        pan2.add(queues);
        pan2.add(queuesOpened);
        pan2.add(time1);
        pan2.add(waitingTime);
        pan2.add(time2);
        pan2.add(serviceTime);
        pan2.add(hour);
        pan2.add(peakHour);

        content=new JTextPane();
        content.setText("Aici se vor afisa informatii curente despre clienti\n\n");
        pane=new JScrollPane(content);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel pan3 = new JPanel();
        pan3.setLayout(new BoxLayout(pan3, BoxLayout.X_AXIS));
        pan3.add( Box.createRigidArea(new Dimension(20,0)) );
        pan3.add(pane);
        pan3.add( Box.createRigidArea(new Dimension(40,0)) );
        pan3.add(pan2);
        pan3.add( Box.createRigidArea(new Dimension(20,0)) );

        JPanel pan4 = new JPanel();
        pan4.setLayout(new BoxLayout(pan4, BoxLayout.Y_AXIS));
        pan4.add( Box.createRigidArea(new Dimension(0,10)) );
        pan4.add(pan1);
        pan4.add( Box.createRigidArea(new Dimension(0,20)) );
        pan4.add(pan3);
        pan4.add( Box.createRigidArea(new Dimension(0,20)) );

        this.start.addActionListener(this);

        frame2.add(pan4);
        frame2.setVisible(true);
        frame2.pack();
    }

    public void showErrorMessage(String message, JTextField text){
        text.setText(message);
        text.setForeground(Color.RED);
    }

    public JFrame getFrame2() {
        return frame2;
    }

    public JTextField getText1() {
        return text1;
    }

    public JTextField getText2() {
        return text2;
    }

    public JTextField getText3() {
        return text3;
    }

    public JTextField getText4() {
        return text4;
    }

    public JTextField getText5() {
        return text5;
    }

    public JTextField getText6() {
        return text6;
    }

    public JTextField getText7() {
        return text7;
    }

    public void setContent(String mesaj) {
        this.content.setText(mesaj);
    }

    public void setTime(String mesaj) {
        this.time.setText(mesaj);
    }

    public void setServiceTime(String mesaj) {
        this.serviceTime.setText(mesaj);
    }

    public void setWaitingTime(String mesaj) {
        this.waitingTime.setText(mesaj);
    }

    public void setPeakHour(String mesaj) {
        this.peakHour.setText(mesaj);
    }

    public void setQueuesOpened(String mesaj) {
        this.queuesOpened.setText(mesaj);
    }

    public void setClientsWaiting(String mesaj) {
        this.clientsWaiting.setText(mesaj);
    }

    public void setClientsArrived(String mesaj) {
        this.clientsArrived.setText(mesaj);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int flag = 0;
        int nrClients = 0;
        int nrQueues = 0;
        int simTime = 0;
        int minArrival = 0;
        int maxArrival = 0;
        int minWaiting = 0;
        int maxWaiting = 0;

        getText1().setForeground(null);
        getText2().setForeground(null);
        getText3().setForeground(null);
        getText4().setForeground(null);
        getText5().setForeground(null);
        getText6().setForeground(null);
        getText7().setForeground(null);

        try {
            nrClients = Integer.parseInt(getText1().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText1());
        }
        try {
            nrQueues = Integer.parseInt(getText2().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText2());
        }
        try {
            simTime = Integer.parseInt(getText3().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText3());
        }
        try {
            minArrival = Integer.parseInt(getText4().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText4());
        }
        try {
            maxArrival = Integer.parseInt(getText5().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText5());
        }
        try {
            minWaiting = Integer.parseInt(getText6().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText6());
        }
        try {
            maxWaiting = Integer.parseInt(getText7().getText());
        } catch (NumberFormatException ex) {
            flag = 1;
            showErrorMessage("introduceti un numar valid!!", getText7());
        }

        if (minArrival > maxArrival) {
            flag = 1;
            showErrorMessage("wrong input!!", getText4());
            showErrorMessage("wrong input!!", getText5());
        }
        if (minWaiting > maxWaiting) {
            flag = 1;
            showErrorMessage("wrong input!!", getText6());
            showErrorMessage("wrong input!!", getText7());
        }

        if (nrClients < 0) {
            showErrorMessage("numbers must be pozitive!!", getText1());
        }
        if (nrQueues < 0) {
            showErrorMessage("numbers must be pozitive!!", getText2());
        }
        if (simTime < 0) {
            showErrorMessage("numbers must be pozitive!!", getText3());
        }
        if (minArrival < 0) {
            showErrorMessage("numbers must be pozitive!!", getText4());
        }
        if (minWaiting < 0) {
            showErrorMessage("numbers must be pozitive!!", getText6());
        }

        if (flag == 0) {
            getFrame2().setVisible(true);
            SimulationManager simulation = new SimulationManager(new File("out_file.txt"), this);
            simulation.numberOfClients=nrClients;
            simulation.numberOfServers=nrQueues;
            simulation.timeLimit=simTime;
            simulation.minArrivalTime=minArrival;
            simulation.maxArrivalTime=maxArrival;
            simulation.minServiceTime=minWaiting;
            simulation.minServiceTime=maxWaiting;

            Thread mainThread = new Thread(simulation);
            mainThread.start();
            try {
                mainThread.join();
            } catch (InterruptedException ex1) {
                System.out.println(ex1.getMessage());
            }
        }
    }

}
