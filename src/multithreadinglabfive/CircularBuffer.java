/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadinglabfive;

/**
 * @author hamda
 */
// Synchronizing access to a shared three-element bounded buffer.
public class CircularBuffer implements Buffer {

    private final int[] buffer = new int[10];//{-1, -1, -1}; // shared buffer
    private int occupiedCells = 0; // count number of buffers used
    private int writeIndex = 0; // index of next element to write to
    private int readIndex = 0; // index of next element to read

    private boolean producerFinished = false; //flag to indicate when the producer has finished producing.


    // place value into buffer
    public synchronized void set(int value) throws InterruptedException {
// wait until buffer has space available, then write value;   
// while no empty locations, place thread in blocked state
        while (occupiedCells == buffer.length) {
            System.out.printf("Housing Department is full. Uqu waits.\n");
            wait(); // wait until a buffer cell is free
        } // end while
        buffer[writeIndex] = value; // set new buffer value

// update circular write index
        writeIndex = (writeIndex + 1) % buffer.length;

        ++occupiedCells; // one more buffer cell is full
        displayState("Uqu writes " + value);
        notifyAll(); // notify threads waiting to read from buffer

        if (value == 10) {
            producerFinished = true;
            notifyAll(); // Notify waiting consumers that the producer is done.
        }
    } // end method set


    // return value from buffer
    public synchronized int get() throws InterruptedException {
// wait until buffer has data, then read value;
// while no data to read, place thread in waiting state
        while (occupiedCells == 0 && !producerFinished) {
            System.out.printf("Housing Department is empty. Student waits.\n");
            wait(); // wait until a buffer cell is filled
        } // end while
        if (producerFinished && occupiedCells == 0) {
            return -1; // Exit the consumer thread if the producer has finished and the buffer is empty.
        }
        int readValue = buffer[readIndex]; // read value from buffer

// update circular read index
        readIndex = (readIndex + 1) % buffer.length;

        --occupiedCells; // one fewer buffer cells are occupied
        displayState("Student reads " + readValue);
        notifyAll(); // notify threads waiting to write to buffer

        return readValue;
    }

    // display current operation and buffer state
    public void displayState(String operation) {
        // output operation and number of occupied buffer cells
        System.out.printf("%s%s%d)%n%s", operation, " (Housing Department cells occupied: ", occupiedCells, "Housing Department cells:");
        System.out.println();
        for (int value : buffer) {
            System.out.printf(" %3d ", value); // Output values in buffer with three spaces for alignment
        }
        System.out.println(); // Move to the next line for dashes

        // Output dashes
        for (int i = 0; i < buffer.length; i++) {
            System.out.print("---- "); // Output four dashes for each buffer cell
        }
        System.out.println(); // Move to the next line for index markers

        // Output write and read index markers
        for (int i = 0; i < buffer.length; i++) {
            if (i == writeIndex && i == readIndex) {
                System.out.print("  WR"); // both write and read index
            } else if (i == writeIndex) {
                System.out.print("  W  "); // just write index
            } else if (i == readIndex) {
                System.out.print("  R  "); // just read index
            } else {
                System.out.print("    "); // neither index
            }
        }
        System.out.println(); // Move to the next line for clarity
    }
} 

