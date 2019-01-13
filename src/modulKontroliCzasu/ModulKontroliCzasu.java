package modulKontroliCzasu;

import java.util.TimerTask;
import main.Reminder;

/**
 *
 * @author Bombisz
 */
public class ModulKontroliCzasu extends TimerTask {
        
    public boolean flaga;

    public ModulKontroliCzasu(){
    }

    public ModulKontroliCzasu(boolean flaga) {
        this.flaga = flaga;
    }
        
    public boolean isFlaga() {
        return flaga;
    }

    public void setFlaga(boolean flaga) {
        this.flaga = flaga;
    }
        
        @Override
        public void run() {
            if(flaga == true){
            System.out.format("Time's up!%n");//sygnalizuje osiągniecie limitu czasu timer
            Reminder.timer.cancel();
            }
            if(flaga == false){
                System.out.println("\u001B[35m" +"modul kontroli czasu->" + "utrzymuj tryb pulsacyjny"  + "\u001B[0m");
                while(true) {
                    try {
                        //dziala w tle
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
