package Wyswietlacz;

import kontrolaObrotow.ModulKontroliObrotow;
import modulKontroliTemp.ModulKontroliTemp;

/**
 *
 * @author Bombisz
 */
public class Wyswietlacz {

    public Wyswietlacz() {
        statystyki();
    }
    
    public void statystyki(){
        
        System.out.println("Temp. " + ModulKontroliTemp.counterTemp);
    }
}
