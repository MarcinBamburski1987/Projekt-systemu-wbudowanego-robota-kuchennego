package main;

import WybierzProgram.WybierzProgram;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kontrolaObrotow.ModulKontroliObrotow;
import modulKontroliTemp.ModulKontroliTemp;
import panelLed.PanelLed;

/**
 *
 * @author Bombisz
 */
public class Main extends Application {
    
        Button btnTurbo = new Button();
        Button btnPulse = new Button();
        Button btnOnOff = new Button();
        Button btnObroty = new Button();
        Button btnTimer = new Button();
        Button btnCzasProgramu = new Button();
        Button btnTemp = new Button();
        Button btnListaPro = new Button();
        Button btnReset = new Button();
        
        static public Label sygnalizator;
        private int counterObroty=0;
        static public boolean obrotyNW=false;
        static public int counterTemp=-20;
        private int counterTurbo=0;
        private int counterPulse=0;
        static public ModulKontroliTemp modulKontroliTemp;
        public int counter = 0;
        
        static public ThreadGroup tg1 = new ThreadGroup("Group A"); 
        Thread niskieStaleObrotyThread,wysokieStaleObrotyThread;
        public boolean isAliveW = false, isAliveN = false;
        public Thread modulKontroliTempThread;
        Thread threadPulse;
        
        private void buttonsInit(){
            btnTurboInit();
            btnPulseInit();
            btnOnOffInit();
            btnObrotyInit();
            btnTimerInit();
            btnCzasProInit();
            btnTempInit(); 
            btnListaInit();
            btnResetInit();
        }
        
        private void btnTurboInit(){
            btnTurbo.setText("Tryb turbo");
            btnTurbo.setMinWidth(150.00);
            btnTurbo.setOnAction((ActionEvent event) -> {
                if((counterTurbo % 2) == 0){
                    System.out.println("Turbo włączone");
                }
                if((counterTurbo % 2) != 0){
                    System.out.println("Turbo wyłączone");
                }
                counterTurbo++;
            }); 
        }
        
        private void btnPulseInit(){
            btnPulse.setText("Tryb pulsacyjny");
            btnPulse.setMinWidth(150.00);
            btnPulse.setOnAction((ActionEvent event) -> {
                ModulKontroliObrotow mko = new ModulKontroliObrotow(false);
                if((counterPulse % 2) == 0){
                        threadPulse = new Thread(tg1,mko);
                        threadPulse.start();
                    System.out.println("\u001B[35m" + "is thread alive after start-> " + threadPulse.isAlive() + "\u001B[0m");
                }
                if((counterPulse % 2) != 0){
                    ModulKontroliObrotow modulKontroliObrotow = new ModulKontroliObrotow();
                }
                counterPulse++;
            }); 
        }
        
        private void btnOnOffInit(){
            btnOnOff.setText("ON/OFF");
            btnOnOff.setMaxWidth(200.0);
            btnOnOff.setStyle("-fx-font: 22 arial; -fx-base: #FA0F0F;");
            btnOnOff.setOnAction((ActionEvent event) -> {
                if((counter % 2) == 0){
                    System.out.println("ON");
                    btnOnOff.setStyle("-fx-font: 22 arial; -fx-base: #1DDF30;");
                    ModulKontroliTemp modulKontroliTemp1 = new ModulKontroliTemp(3,counterTemp);
                    utrzymujTemp();
                    PanelLed panelLed = new PanelLed();
                }
                if((counter % 2) != 0){
                    System.out.println("OFF");
                    btnOnOff.setStyle("-fx-font: 22 arial; -fx-base: #FA0F0F;");
                    sygnalizator.setTextFill(Color.web("red"));
                    wylaczObroty();
                }
                counter++;
            }); 
        }
        
        public void obrotyNiskie(){
                if(counterObroty % 2 == 0){
                    obrotyNW=false;
                    if(isAliveW == true){
                        ModulKontroliObrotow.setIsRunning(false);
                        wysokieStaleObrotyThread.interrupt();
                        try {
                            wysokieStaleObrotyThread.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    ModulKontroliObrotow mko = new ModulKontroliObrotow(true);
                    niskieStaleObrotyThread = new Thread(tg1,mko);
                    
                    if(niskieStaleObrotyThread.isAlive() == false){
                        isAliveN=true;
                        ModulKontroliObrotow.setIsRunning(true);
                        niskieStaleObrotyThread.start();
                    }
                }
        }
        
        public void obrotyWysokie(){
            if(counterObroty % 2 != 0){
                    obrotyNW=true;
                    if(isAliveN=true){
                       ModulKontroliObrotow.setIsRunning(false);
                       niskieStaleObrotyThread.interrupt();
                            try {
                                niskieStaleObrotyThread.join();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    
                    ModulKontroliObrotow mko = new ModulKontroliObrotow(true);
                    wysokieStaleObrotyThread = new Thread(tg1,mko);
                    
                    if(wysokieStaleObrotyThread.isAlive() == false){
                        isAliveW = true;
                        ModulKontroliObrotow.setIsRunning(true);
                        wysokieStaleObrotyThread.start();
                    }
                }
        }
        
        public void wylaczObroty(){
            ModulKontroliObrotow.setIsRunning(false);
                    niskieStaleObrotyThread.interrupt();
                    wysokieStaleObrotyThread.interrupt();
                try {
                    niskieStaleObrotyThread.join();
                    wysokieStaleObrotyThread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("tg1 -> " + tg1.activeCount());
        }
        
        private void btnObrotyInit(){
            btnObroty.setText("Ustaw obroty");
            btnObroty.setMinWidth(150.00);
            btnObroty.setOnAction((ActionEvent event) -> {
                obrotyNiskie();
                obrotyWysokie();
                counterObroty++;
            }); 
        }
        
        private void btnTimerInit(){
            btnTimer.setText("Timer");
            btnTimer.setMinWidth(150.00);
            btnTimer.setOnAction((ActionEvent t) -> {
                System.out.println("Wprowadź czas timera w sekundach: ");
                Scanner timerIncSc = new Scanner(System.in);
                int timerInc = timerIncSc.nextInt();
                Reminder reminder = new Reminder(timerInc);
                Thread th = new Thread(reminder);
               
            });
        }
        
        private void btnCzasProInit(){
            btnCzasProgramu.setText("Ustaw czas programu");
            btnCzasProgramu.setMinWidth(150.00);
            btnCzasProgramu.setOnAction((ActionEvent event) -> {
                
            }); 
        }
        
        private void btnTempInit(){
            btnTemp.setText("Ustaw temp.");
            btnTemp.setMinWidth(150.00);
            btnTemp.setOnAction((ActionEvent event) -> {
                if(counterTemp == 120){
                    counterTemp=-20;
                }
                counterTemp+=20;
                System.out.println("temp." + counterTemp);
            }); 
        }
        
        
        
        private void utrzymujTemp(){
            modulKontroliTemp = new ModulKontroliTemp(counterTemp);
            modulKontroliTempThread = new Thread(tg1,modulKontroliTemp);
            modulKontroliTempThread.start();
        }
        
        private void btnListaInit(){
            btnListaPro.setText("Lista prog.");
            btnListaPro.setMinWidth(150.00);
            btnListaPro.setOnAction((ActionEvent event) -> {
                WybierzProgram wybierzProgram = new WybierzProgram();
            }); 
        }
        
        private void btnResetInit(){
            btnReset.setText("Reset prog.");
            btnReset.setMinWidth(150.00);
            btnReset.setOnAction((ActionEvent event) -> {
                WybierzProgram wybierzProgram = new WybierzProgram("Domyslny");
                
            }); 
        }
    
    @Override
    public void start(Stage primaryStage) {
        buttonsInit();
        
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.TOP_LEFT); 
        hbButtons.getChildren().addAll(btnTurbo,btnPulse,btnObroty);
        
        HBox hbButtons1 = new HBox();
        hbButtons1.setSpacing(10.0);
        hbButtons1.setAlignment(Pos.TOP_LEFT); 
        hbButtons1.getChildren().addAll(btnTimer,btnCzasProgramu,btnTemp);
        
        HBox hbButtons2 = new HBox();
        hbButtons2.setSpacing(10.0);
        hbButtons2.setAlignment(Pos.CENTER); 
        hbButtons2.getChildren().addAll(btnListaPro,btnReset);
        
        sygnalizator = new Label("Gotowość modułu");
        sygnalizator.setStyle("-fx-border-color: black;");
        sygnalizator.setTextFill(Color.web("red"));
        
        VBox vboxMainLayout = new VBox(); 
        vboxMainLayout.setSpacing(10.0);
        vboxMainLayout.setAlignment(Pos.TOP_CENTER); 
        vboxMainLayout.getChildren().addAll(btnOnOff,hbButtons,hbButtons1,hbButtons2,sygnalizator);
        
        Scene scene = new Scene(vboxMainLayout, 470, 250);
        
        primaryStage.setTitle("Robot kuchenny");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
