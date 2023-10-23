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

public class Producer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation; // reference to shared object

// constructor
    public Producer(Buffer shared) {
        sharedLocation = shared;
    } // end Producer constructor

// store values from 1 to 10 in sharedLocation
    public void run() {
        int sum = 0;

        for (int count = 1; count <= 10; count++) {
            try // sleep 0 to 3 seconds, then place value in Buffer
            {
                Thread.sleep(generator.nextInt(3000)); // random sleep
                sharedLocation.set(count); // set value in buffer
                sum += count; // increment sum of values
                //System.out.printf( "\t%2d\n", sum );
            } 
            // if lines 25 or 26 get interrupted, print stack trace
            catch (InterruptedException exception) {
                exception.printStackTrace();
            } 
        } 
        System.out.println(
                "Uqu done producing\nTerminating Uqu");
    }
}
