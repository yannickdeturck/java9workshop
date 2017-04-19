package be.ordina.jworks.java9samples;

/**
 * DeprecatedAnnotation annotation.
 *
 * @author Yannick De Turck
 */
public class DeprecatedAnnotation {
    @Deprecated(since = "1.0", forRemoval = false)
    public void someAncientMethod(){
        System.out.println("Doing ancient stuff...");
    }
}
