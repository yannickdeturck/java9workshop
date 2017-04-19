package be.ordina.jworks.java9samples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture API improvements.
 *
 * @author Yannick De Turck
 */
public class CompletableFutureAPIImprovements {
    public static void main(String[] args) {
        // completeAsync
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("calculated value1");
        future.whenComplete((s, throwable) -> System.out.println(s));
        future = new CompletableFuture<>();
        CompletableFuture<String> completedAsync =
                future.completeAsync(() -> "calculated value2");
        completedAsync.whenComplete((s, throwable) -> System.out.println(s));

        // orTimeout
        CompletableFuture someFuture = new CompletableFuture().orTimeout(1, TimeUnit.SECONDS);

        // completedOnTimeout
        CompletableFuture<Object> otherFuture = new CompletableFuture<>();
        otherFuture.completeOnTimeout("alternative result", 3, TimeUnit.SECONDS);
    }

    class MyCompletableFuture<T> extends CompletableFuture<T> {
        @Override
        public <U> CompletableFuture<U> newIncompleteFuture() {
            return new MyCompletableFuture<>();
        }
    }
}
