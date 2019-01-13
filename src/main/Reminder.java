package main;

import java.util.Timer;
import modulKontroliCzasu.ModulKontroliCzasu;
 
public class Reminder extends Thread{
    public static Timer timer;
    
    public Reminder() {
    }
    
     public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new ModulKontroliCzasu(true), seconds*1000);
    }
}
