package kontrolaObrotow;

import modulKontroliCzasu.ModulKontroliCzasu;
import main.Main;
/**
 *
 * @author Bombisz
 */
public class ModulKontroliObrotow extends Thread{
    public boolean flag;
    public ModulKontroliCzasu modulKontroliCzasu;
     
    static public boolean isRunning=true;

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        ModulKontroliObrotow.isRunning = isRunning;
    }

    public ModulKontroliObrotow(){
        System.out.println("moduł kontroli -> Obroty wyłączone");
        System.out.println("moduł kontroli ->  tryb pulsacyjny off");
    }
    
    public ModulKontroliObrotow(boolean flag){
        this.flag=flag;
    }

    @Override
    public void run(){
        if(flag == true){
            staleObroty();
        }
        
        if(flag == false){
            trybPulsacyjnyOn();
        }
        
    }
    
    public void staleObroty(){
        while(isRunning) {
            System.out.println("obroty wysokie: " + Main.obrotyNW + "; "+ "stałe obroty; " + "ilość procesów w tg1 " + Main.tg1.activeCount());
        }
    }
    
    
    public void trybPulsacyjnyOn(){
         ModulKontroliCzasu modulKontroliCzasu = new ModulKontroliCzasu(false);
         modulKontroliCzasu.run();
        System.out.println("\u001B[35m" + "moduł kontroli obrotów-> tryb pulsacyjny on" + "\u001B[0m");
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
