/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadinglabfive;

/**
 * @author hamda
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularBufferTest {

    public static void main(String[] args) {
// create new thread pool with two threads
        ExecutorService application = Executors.newCachedThreadPool();
// create CircularBuffer to store ints
        CircularBuffer sharedLocation = new CircularBuffer();
// display the initial state of the CircularBuffer
        sharedLocation.displayState("Initial State");
// execute the Producer and Consumer tasks
        application.execute(new Producer(sharedLocation));
        // Create and execute multiple Consumer instances with different labels
        Consumer consumer1 = new Consumer(sharedLocation, "Consumer-Sama");
        SecondConsumer consumer2 = new SecondConsumer(sharedLocation, "Consumer-Tala");

        application.execute(consumer1);
        application.execute(consumer2);
//        application.execute(new Consumer(sharedLocation, "Consumer Zara"));


        application.shutdown();
    }
} 
