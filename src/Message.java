import java.io.Serializable;

/*
  Created by joao on 03/10/15.
 */

public class Message implements Serializable{

    private int pid;
    private String message;
    private boolean ok;
    private boolean election;
    private boolean coordinator;

    public Message(int pid){
        this.pid = pid;
        this.election = true;
    }

    public boolean isElection() {
        return election;
    }

    public Message(int pid, String ok){
        this.message = ok;
        this.pid = pid;
        this.ok = true;
    }

    public boolean isOk() {
        return ok;
    }

    public Message (String message, int pid) {
        this.message = message;
        this.pid = pid;
        this.coordinator = true;
    }

    public int getPid() {
        return pid;
    }
}