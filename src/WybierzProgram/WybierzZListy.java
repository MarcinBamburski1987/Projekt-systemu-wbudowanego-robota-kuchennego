package WybierzProgram;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bombisz
 */
public class WybierzZListy{
    
    public ArrayList listaprogramow;
    public Program p1 = new Program(1,"p1");
    public Program p2 = new Program(2,"p2");
    public Program p3 = new Program(3,"p3");
    public Program p4 = new Program(4,"p4");

    public WybierzZListy() {
        listaprogramow = new ArrayList();
        listaprogramow.add(p1);
        listaprogramow.add(p2);
        listaprogramow.add(p3);
        listaprogramow.add(p4);
    }
    
    public void printList(){
        Iterator itr=listaprogramow.iterator();  
        while(itr.hasNext()){  
            Program program = (Program) itr.next();
            System.out.println("program id " + program.identifikator + " name " + program.programName );
        } 
    }
}
