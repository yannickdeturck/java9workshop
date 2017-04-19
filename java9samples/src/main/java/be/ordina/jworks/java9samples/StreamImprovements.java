package be.ordina.jworks.java9samples;

import be.ordina.jworks.java9samples.model.BlogPost;

import java.util.stream.Stream;

/**
 * Stream improvements.
 *
 * @author Yannick De Turck
 */
public class StreamImprovements {
    public static void main(String[] args) {
        // Stream::takeWhile
        Stream.of(1, 4, 2, -8, 6, 3)
                .takeWhile(i -> i > 0)
                .forEach(System.out::println);
        // 1, 4, 2

        // Stream::dropWhile
        Stream.of(1, 4, 2, -8, 6, 3)
                .dropWhile(i -> i > 0)
                .forEach(System.out::println);
        // -8, 6, 3

        // Stream::iterate
        Stream.iterate(1, i -> i < 20, i -> i + 2)
                .forEach(System.out::println);
        // 1, 3, 5, ..., 19

        // Stream::ofNullable
        BlogPost post = findBlogPost(1L); // evil method that may return null
        Stream<String> comments =
                post == null ? Stream.empty() : post.getComments().stream();

        post = findBlogPost(1L); // evil method that may return null
        comments = Stream.ofNullable(post)
                .flatMap(bpost -> bpost.getComments().stream());
    }



    private static BlogPost findBlogPost(Long id) {
        return new BlogPost("What's new in Java 9", "java 9");
    }
}
