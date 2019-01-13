package modulKontroliTemp;

import java.util.Timer;

/**
 *
 * @author Bombisz
 */
public class ModulKontroliTemp extends Thread{
    
    static public int counterTemp;
    public Timer t;
    
    public ModulKontroliTemp(int counterTemp) {
        ModulKontroliTemp.counterTemp = counterTemp;
    }
    
    public ModulKontroliTemp(int sec, int counterTemp) {
        t = new Timer();
        t.schedule(new SygnalizacjaOsiagnietejTemp(counterTemp), sec*1000);
    }
    
    @Override
    public void run(){
        System.out.println("utrzymuj temp. " + counterTemp);
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
