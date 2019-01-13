package WybierzProgram;

/**
 *
 * @author Bombisz
 */
public class Program extends Thread{
    public int identifikator;
    public String programName;

    public Program(int identifikator, String programName) {
        this.identifikator = identifikator;
        this.programName = programName;
    }

    public int getIdentifikator() {
        return identifikator;
    }

    public void setIdentifikator(int identifikator) {
        this.identifikator = identifikator;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
    @Override
    public void run(){
        System.out.println("startuje program");
    }
}
