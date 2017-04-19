package be.ordina.jworks.java9samples.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Yannick De Turck
 */
public class BlogPost {
    private String title;
    private String category;
    private List<String> comments = new ArrayList<>();

    public BlogPost(String title, String category) {
        this.title = title;
        this.category = category;
        for (int i = 0; i < new Random().nextInt(5); i++) {
            comments.add("comment" + i);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getComments() {
        return comments;
    }
}
