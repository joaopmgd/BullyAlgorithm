/**
 * Created by joao on 03/10/15.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManageRequisition extends Thread{

    private Socket socket;
    private Server server;

    public ManageRequisition(Socket socket, Server server){
        this.socket=socket;
        this.server=server;
    }

    public void readMessage(){
        try {
            while(true) {
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) input.readObject();
                if (message.isElection() && message.getPid() < server.getPid()) {
                    System.out.println("Process: "+server.getPid()+" is sending OK message to Process: "+message.getPid());
                   ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    Message reply = new Message(server.getPid(),"ok");
                    out.writeObject(reply);
                    server.startElection();
                }else{
                    System.out.println("Process: "+server.getPid()+" has ACCEPTED that Process: "+message.getPid()+" is COORDINATOR");
                    this.server.setCoordinator(message.getPid());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        readMessage();
    }

    public void closeConnection(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}