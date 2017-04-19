package be.ordina.jworks.java9samples;

/**
 * Private methods in interfaces.
 *
 * @author Yannick De Turck
 */
public class PrivateMethodsInInterfaces {
    // Java 8
    interface PackageSenderJava8 {
        default void prepare() {
            // prepare stuff logic right here
        }

        default void cleanUp () {
            // cleanup stuff logic right here
        }

        default void sendPackage(Package thePackage) {
            prepare();
            // sending package logic right here
            cleanUp();
        }
    }

    // Java 9
    public interface PackageSenderJava9 {
        private void prepare() {
            // prepare stuff logic right here
        }

        private void cleanUp () {
            // cleanup stuff logic right here
        }

        default void sendPackage(Package thePackage) {
            prepare();
            // sending package logic right here
            cleanUp();
        }
    }
}
