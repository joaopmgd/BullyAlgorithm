/*
  Created by joao on 03/10/15.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client extends Thread{

    private ArrayList<ConnectedClient> connectedClients;
    private Process process;

    public Client(Process process){
        this.process = process;
        this.connectedClients = new ArrayList<ConnectedClient>();
    }

    public void connect(String ip, int port, int pid){
        try{
            Socket socket = new Socket(ip,port);
            connectedClients.add(new ConnectedClient(pid,socket));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Response(ArrayList<Socket> socketList){
        try {
            for (Socket s: socketList){
                ObjectInputStream input = new ObjectInputStream(s.getInputStream());
                Message message = (Message) input.readObject();
                if (message.isOk()) {
                    System.out.println("Process: "+process.getPid()+" got an OK from Process: "+message.getPid());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        try {
            ArrayList<Socket> socketList = new ArrayList<>();
            Message message = new Message(process.getPid());
            for(ConnectedClient connectedClient: this.connectedClients){
                if (connectedClient.getPid() > process.getPid()){
                    System.out.println("Process: "+process.getPid()+" is sending ELECTION message to Process: "+connectedClient.getPid());
                    ObjectOutputStream out = new ObjectOutputStream(connectedClient.getSocket().getOutputStream());
                    out.writeObject(message);
                    socketList.add(connectedClient.getSocket());
                }
            }
            if (socketList.isEmpty()){
                process.setCoordinator(process.getPid());
                System.out.println("Process: " + process.getPid() + " is the new COORDINATOR");
                message = new Message("Coordinator",process.getPid());
                for(ConnectedClient connectedClient: this.connectedClients){
                    System.out.println("Process: "+process.getPid()+" is sending COORDINATOR message to Process: "+connectedClient.getPid());
                    ObjectOutputStream out = new ObjectOutputStream(connectedClient.getSocket().getOutputStream());
                    out.writeObject(message);
                }
            }else{
                Response(socketList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            for(ConnectedClient connectedClient : this.connectedClients)
                connectedClient.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        sendMessage();
    }
}