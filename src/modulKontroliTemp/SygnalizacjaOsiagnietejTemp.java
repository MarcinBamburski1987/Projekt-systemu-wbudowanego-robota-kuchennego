/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulKontroliTemp;

import java.util.TimerTask;

/**
 *
 * @author Bombisz
 */
class SygnalizacjaOsiagnietejTemp extends TimerTask {

    static public int counterTemp;

    public SygnalizacjaOsiagnietejTemp() {
    }

    SygnalizacjaOsiagnietejTemp(int counterTemp) {
        SygnalizacjaOsiagnietejTemp.counterTemp = counterTemp;
    }

    @Override
    public void run() {
        System.out.println("Osiagnięto temp. " + counterTemp );
    }
    
}
