package controller;

import java.util.Timer;

public class SchedulerMain {

//    public static void main(String args[]) throws InterruptedException {
//
//        Timer time = new Timer(); // Instantiate Timer Object
//        Schedule st = new Schedule(); // Instantiate SheduledTask class
//        time.schedule(st, 0, 10000); // Create Repetitively task for every 1 secs
//
//        //for demo only.
//        for (int i = 0; i <= 5; i++) {
//            System.out.println("Execution in Main Thread....");
//            Thread.sleep(10000);
//            if (i == 0) {
//                System.out.println("Application Terminates");
//                System.exit(0);
//            }
//        }
//    }

    public static void schedule() throws InterruptedException {
        Timer time = new Timer();
        Schedule sc = new Schedule();
        time.schedule(sc, 0, 10000);
        for(int i = 0; i <= 2; i++){
            Thread.sleep(10000);
            if(i == 2){
                System.exit(0);
            }
        }
    }
}
