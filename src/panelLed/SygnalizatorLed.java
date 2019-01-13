package panelLed;

import java.util.TimerTask;
import javafx.scene.paint.Color;
import main.Main;

/**
 *
 * @author Bombisz
 */
class SygnalizatorLed extends TimerTask{
    static public Main main;

    public SygnalizatorLed() {
    }

    @Override
    public void run() {
        System.out.println("Gotowość modułu");
        Main.sygnalizator.setTextFill(Color.web("green"));
    }
    
}
