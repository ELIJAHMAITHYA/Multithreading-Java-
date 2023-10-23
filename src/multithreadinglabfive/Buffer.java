/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreadinglabfive;

/**
 *
 * @author hamda
 */
public interface Buffer {
// place int value into Buffer
    public void set(int value) throws InterruptedException;
// return int value from Buffer
    public int get() throws InterruptedException;
}
 
