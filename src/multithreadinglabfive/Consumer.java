/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadinglabfive;
/**
 *
 * @author hamda
 */
import java.util.Random;

public class Consumer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation; // reference to shared object
 private final String label;

    public Consumer(Buffer shared, String label) {
        sharedLocation = shared;
        this.label = label;
    }

// read sharedLocation's value 10 times and sum the values
    public void run() {
        int sum = 0;

        for (int count = 1; count <= 10; count++) {
// sleep 0 to 3 seconds, read value from buffer and add to sum
            try {
                Thread.sleep(generator.nextInt(3000));
//                sum += sharedLocation.get();
                int value = sharedLocation.get();
                sum += value;
              //  System.out.printf( "\t\t\t%2d\n", sum );
              if(value != -1){
                 System.out.printf("%s read value %d\n", label, value);
              }

            } // end try
            // if lines 26 or 27 get interrupted, print stack trace
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
//        System.out.printf("\n%s %d\n%s\n",
//                "Consumer read values totaling", sum, "Terminating Consumer");
        System.out.printf("%s read values totaling %d\nTerminating %s\n", label, sum, label);
    }
}

//    public void run() {
//        int sum = 0;
//
//        for (int count = 1; count <= 10; count++) {
//            // read a constant value (e.g., 10)
//            int value = 10;
//            sum += value;
//            System.out.printf("%s read value %d\n", label, value);
//
//            try

