package be.ordina.jworks.java9samples;

import be.ordina.jworks.java9samples.model.Package;

import java.util.List;

/**
 * Diamond operator for anonymous inner classes
 *
 * @author Yannick De Turck
 */
public class DiamondOperatorAnonInnerClasses {
    public static void main(String[] args) {
        createPackageJava8("content");
        createPackageJava9("content");
        createPackageJava9NonDenotableInferredType("content");
    }

    private static <T> Package<T> createPackageJava8(T packageContent) {
        // How it needs to be written in Java 8
        return new Package<T>(packageContent){};
    }

    private static <T> Package<T> createPackageJava9(T packageContent) {
        // In Java 8 this returns a "Cannot use <> with anonymous classes" error, whereas in Java 9, this is okay
        return new Package<>(packageContent) {};
    }

    private static Package<?> createPackageJava9NonDenotableInferredType(Object packageContent) {
        List<?> someList = List.of(packageContent);
        // Also print the "Cannot use <> with anonymous classes" error
        // return new Package<>(someList) {};
        // Needs to be modified like this
        return new Package<List<?>>(someList) {};
    }
}
