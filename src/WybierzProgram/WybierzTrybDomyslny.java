package WybierzProgram;

/**
 *
 * @author Bombisz
 */
public class WybierzTrybDomyslny{
    public Program program = new Program(0,"Default");

    public WybierzTrybDomyslny() {
    }
        
    public WybierzTrybDomyslny(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
