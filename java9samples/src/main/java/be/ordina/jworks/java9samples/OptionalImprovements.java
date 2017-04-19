package be.ordina.jworks.java9samples;

import be.ordina.jworks.java9samples.model.BlogPost;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional improvements.
 *
 * @author Yannick De Turck
 */
public class OptionalImprovements {
    public static void main(String[] args) {
        // Optional::stream
        // java 8
        Stream<BlogPost> blogPosts = findBlogPosts("java")
                .filter(Optional::isPresent)
                .map(Optional::get);
        // java 9
        blogPosts = findBlogPosts("java")
                .flatMap(Optional::stream);

        // ifPresentOrElse
        Optional<Integer> optionalInt = Optional.of(7);
        optionalInt.ifPresentOrElse(
                i -> System.out.println("number is " + i),
                () -> System.out.println("it's empty")
        );

        // or
        Optional<BlogPost> blogPost = findBlogPost(1L)
                .or(() -> Optional.of(new BlogPost("dummy", "dummy")));
    }

    private static Stream<Optional<BlogPost>> findBlogPosts(String category) {
        return Stream.of(
                Optional.of(new BlogPost("What's new in Java 9", "java 9")),
                Optional.of(new BlogPost("Unit testing with JUnit5", "junit")),
                Optional.empty(),
                Optional.of(new BlogPost("Intro to Spring Boot", "spring")),
                Optional.empty()
        );
    }

    private static Optional<BlogPost> findBlogPost(Long id) {
        return Optional.of(new BlogPost("What's new in Java 9", "java 9"));
    }
}