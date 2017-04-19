package be.ordina.jworks.java9samples;

import be.ordina.jworks.java9samples.model.BlogPost;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Collectors improvements.
 *
 * @author Yannick De Turck
 */
public class CollectorsImprovements {
    public static void main(String[] args) {
        // counting the numbers in a list
        // filter
        List<Integer> numbers = List.of(1, 2, 3, 5, 5);
        Map<Integer, Long> result = numbers.stream()
                .filter(val -> val > 3)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        result.forEach((key, val) -> System.out.println(key + " -> " + val));
        // 5 -> 2

        // filtering
        result = numbers.stream()
                .collect(Collectors.groupingBy(i -> i,
                        Collectors.filtering(val -> val > 3, Collectors.counting())));
        result.forEach((key, val) -> System.out.println(key + " -> " + val));
        // 1 -> 0, 2 -> 0, 3 -> 0, 5 -> 2


        // get the comments for each blogpost category
        List<BlogPost> blogposts = findBlogPosts();
        Map<String, List<List<String>>> blogPostComments = blogposts.stream()
                .collect(Collectors.groupingBy(BlogPost::getCategory,
                        Collectors.mapping(BlogPost::getComments, Collectors.toList())));
        // junit -> [[comment0], [comment0, comment1, comment2]]

        Map<String, List<String>> blogPostComments2 = blogposts.stream()
                .collect(Collectors.groupingBy(BlogPost::getCategory,
                        Collectors.flatMapping(blog -> blog.getComments().stream(),
                                Collectors.toList())));
        // junit -> [comment0, comment0, comment1, comment2]
    }

    private static List<BlogPost> findBlogPosts() {
        return List.of(
                new BlogPost("What's new in Java 9", "java 9"),
                new BlogPost("Unit testing with JUnit5", "junit"),
                new BlogPost("Intro to Spring Boot", "spring")
        );
    }
}
