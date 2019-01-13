package WybierzProgram;

import panelLed.PanelLed;

/**
 *
 * @author Bombisz
 */
public final class WybierzProgram{
    
    public WybierzZListy wybierzZListy = new WybierzZListy();
    static public int klik=0;
    
    public void domyslny(){
        WybierzTrybDomyslny wybierzTrybDomyslny = new WybierzTrybDomyslny();
        System.out.println("program -> " + " id " + wybierzTrybDomyslny.program.identifikator + " " + wybierzTrybDomyslny.program.programName);
        Thread t0 = new Thread(wybierzTrybDomyslny.program);
        t0.start();
    }
    
    public void program1(){
        Program program;
        program = (Program) wybierzZListy.listaprogramow.get(0);
        Thread t1 = new Thread(program);
        System.out.println("program -> " + " id " + program.identifikator + " " + program.programName);
        t1.start();
    }
    
    public void program2(){
        Program program;
        program = (Program) wybierzZListy.listaprogramow.get(1); 
        Thread t2 = new Thread(program);
        System.out.println("program -> " + " id " + program.identifikator + " " + program.programName);
        t2.start();
    }
    
    public void program3(){
        Program program;
        program = (Program) wybierzZListy.listaprogramow.get(2);
        Thread t3 = new Thread(program);
        System.out.println("program -> " + " id " + program.identifikator + " " + program.programName);
        t3.start();
    }
    
    public void program4(){
        Program program;
        program = (Program) wybierzZListy.listaprogramow.get(3);
        Thread t4 = new Thread(program);
        System.out.println("program -> " + " id " + program.identifikator + " " + program.programName);
        t4.start();
    }
    
    public WybierzProgram(String str){
        System.out.println("tryb->" + str);
        domyslny();
    }
    
    public WybierzProgram(){
        if(klik == 0){
                    domyslny();
                    PanelLed panelLed = new PanelLed();
                }
                if(klik == 1){
                    program1();
                    PanelLed panelLed = new PanelLed();
                }
                if(klik == 2){
                    program2();
                    PanelLed panelLed = new PanelLed();
                }
                if(klik == 3){
                    program3();
                    PanelLed panelLed = new PanelLed();
                }
                if(klik == 4){
                    program4();
                    PanelLed panelLed = new PanelLed();
                    klik=-1;
                }
                klik++;
    }
}
