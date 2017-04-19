package be.ordina.jworks.java9samples.model;

/**
 * @author Yannick De Turck
 */
public class Package<T> {
    private T content;

    public Package(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
