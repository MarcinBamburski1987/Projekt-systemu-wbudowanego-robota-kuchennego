package panelLed;

import java.util.Timer;
import javafx.scene.paint.Color;
import main.Main;

/**
 *
 * @author Bombisz
 */
public class PanelLed extends Thread{
    Timer timer;

    public PanelLed(){
        timer = new Timer();
        timer.schedule(new SygnalizatorLed(), 5*1000);
    }

    @Override
    public void run() {
    }
}

