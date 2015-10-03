import java.net.Socket;

/**
 * Created by joao on 03/10/15.
 */
public class ConnectedClient {

    private Socket socket;
    private int pid;

    public ConnectedClient (int pid, Socket socket){
        this.pid = pid;
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getPid() {
        return pid;
    }
}
