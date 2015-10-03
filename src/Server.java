/*
  Created by joao on 03/10/15.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private ArrayList<Socket> socketList;
    private ArrayList<ManageRequisition> streams;
    private Process process;

    public Server(int port, Process process){
        try {
            this.serverSocket = new ServerSocket(port);
            this.socketList = new ArrayList<Socket>();
            this.streams = new ArrayList<ManageRequisition>();
            this.process = process;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                socketList.add(client);
                ManageRequisition stream = new ManageRequisition(client,this);
                streams.add(stream);
                stream.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCoordinator(int coordinator){
        process.setCoordinator(coordinator);
    }

    public int getPid(){
        return process.getPid();
    }

    public void startElection(){
        if (!process.hasStartedElection()){
            process.startElection();
        }
    }

    @Override
    public void run() {
        receiveMessage();
    }
}