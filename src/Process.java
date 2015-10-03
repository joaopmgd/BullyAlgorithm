import java.util.ArrayList;

/*
  Created by joao on 03/10/15.
 */

public class Process extends Thread{

    private String ip;
    private int port;
    private int pid;
    private Client client;
    private Server server;
    private int coordinator;
    private boolean startedElection;


    public Process(String ip ,int port, int pid){
        this.ip = ip;
        this.port = port;
        this.pid = pid;
        this.client = new Client(this);
        this.startedElection = false;
    }

    public int getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(int coordinator){
        this.coordinator = coordinator;
    }

    public int getPid() {
        return pid;
    }

    public void startListening(){
        this.server = new Server(port,this);
        server.start();
    }

    public void connectAll(String ip, ArrayList<Process> processList){
        for (Process process: processList) {
            if(process.getPid() != this.pid){
                System.out.println("Process: "+this.pid+" is connecting to process "+ process.getPid());
                this.client.connect(ip, process.getPort(), process.getPid());
            }
        }
    }

    public int getPort() {
        return port;
    }

    public void startElection(){
        System.out.println("Process: "+this.pid+" is stating an election");
        client.start();
        this.startedElection = true;
    }

    public boolean hasStartedElection() {
        return startedElection;
    }

    @Override
    public void run(){
        startListening();
    }
}

