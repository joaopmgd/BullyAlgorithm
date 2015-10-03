import java.util.ArrayList;

/**
 * Created by joao on 03/10/15.
 */
public class Main {

    public static void main(String args[]){

        ArrayList<Process> processList = new ArrayList<>();
        Process p1 = new Process("localhost",3001,1);
        processList.add(p1);
        Process p2 = new Process("localhost",3002,2);
        processList.add(p2);
        Process p3 = new Process("localhost",3003,3);
        processList.add(p3);
        Process p4 = new Process("localhost",3004,4);
        processList.add(p4);
        Process p5 = new Process("localhost",3005,5);
        processList.add(p5);
        Process p6 = new Process("localhost",3006,6);
        processList.add(p6);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

        p1.connectAll("localhost", processList);
        p2.connectAll("localhost", processList);
        p3.connectAll("localhost", processList);
        p4.connectAll("localhost", processList);
        p5.connectAll("localhost", processList);
        p6.connectAll("localhost", processList);

        p1.startElection();



//        p1.closeConnection();
//        p2.closeConnection();
//        p3.closeConnection();
    }
}